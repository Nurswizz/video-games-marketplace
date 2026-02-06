package src.controllers;

import src.entities.Game;
import src.interfaces.IGamesService;
import src.utils.Validation;

import src.utils.InputReader;

import java.util.Scanner;

import src.services.GamesService;

public class AdminPanelController {

    private final InputReader inputReader;
    private final IGamesService gameService;

    public AdminPanelController(IGamesService gameService) {
        this.inputReader = new InputReader(new Scanner(System.in));
        this.gameService = gameService;
    }

    public void showAdminPanel() {
        boolean inAdminPanel = true;

        while (inAdminPanel) {
            printAdminMenu();
            String choice = inputReader.readLine();

            switch (choice) {
                case "1" -> addGame();
                case "2" -> changeGameInfo();
                case "3" -> deleteGame();
                case "0" -> inAdminPanel = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printAdminMenu() {
        System.out.println("\n===== Admin Panel =====");
        System.out.println("1. Add Game");
        System.out.println("2. Change Game Info");
        System.out.println("3. Delete Game");
        System.out.println("0. Back");
        System.out.print("Choose option: ");
    }

    private void addGame() {
        System.out.println("\n=== Add New Game ===");

        System.out.print("Title: ");
        String title = inputReader.readNonEmpty("Title");

        System.out.println("Release Date:");
        String releaseDate = inputReader.readDate();
        if (releaseDate == null) {
            System.out.println("Game creation cancelled due to invalid date.");
            return;
        }

        System.out.print("Team: ");
        String team = inputReader.readNonEmpty("Team");

        System.out.print("Rating (0-5): ");
        Float rating = inputReader.readFloat();
        if (rating == null || !Validation.isValidRating(rating)) {
            System.out.println("Invalid rating. Must be between 0 and 100.");
            return;
        }

        System.out.print("Times listed: ");
        Integer timesListed = inputReader.readInteger();
        if (timesListed == null || !Validation.isNonNegative(timesListed)) {
            System.out.println("Invalid times listed value. Must be non-negative.");
            return;
        }

        System.out.print("Genres (comma-separated): ");
        String genres = inputReader.readNonEmpty("Genres");

        System.out.print("Summary: ");
        String summary = inputReader.readNonEmpty("Summary");

        Game game = new Game(title, releaseDate, team, rating, timesListed, genres, summary);

        try {
            gameService.addGame(game);
            System.out.println("Game successfully added.");
        } catch (Exception e) {
            System.out.println("Error adding game: " + e.getMessage());
        }
    }

    private void changeGameInfo() {
        System.out.println("\n=== Change Game Info ===");
        System.out.print("Enter game ID: ");

        Long gameId = inputReader.readLong();
        if (gameId == null || !Validation.isValidId(gameId)) {
            System.out.println("Invalid game ID.");
            return;
        }

        Game game = gameService.getGameById(gameId);
        if (game == null) {
            System.out.println("Game not found.");
            return;
        }

        System.out.println("\nPress ENTER to keep current value.");

        String title = inputReader.readWithDefault("Title", game.getTitle());
        String releaseDate = inputReader.readWithDefault("Release date", game.getReleaseDate());
        String team = inputReader.readWithDefault("Team", game.getTeam());
        float rating = inputReader.readFloatWithDefault("Rating", game.getRating());
        int timesListed = inputReader.readIntWithDefault("Times listed", game.getTimesListed());
        String genres = inputReader.readWithDefault("Genres", game.getGenres());
        String summary = inputReader.readWithDefault("Summary", game.getSummary());

        Game updatedGame = new Game(
                game.getId(),
                title,
                releaseDate,
                team,
                rating,
                timesListed,
                genres,
                summary
        );

        gameService.updateGame(updatedGame);

        System.out.println("Game updated successfully.");
    }


    private void deleteGame() {
        System.out.println("\n=== Delete Game ===");
        System.out.print("Enter game ID to delete: ");
        Long gameId = inputReader.readLong();
        if (gameId == null || !Validation.isValidId(gameId)) {
            System.out.println("Invalid game ID.");
            return;
        }

        if (inputReader.readConfirmation("Are you sure?")) {
            try {
                gameService.deleteGame(gameId);
                System.out.println("Game successfully deleted.");
            } catch (Exception e) {
                System.out.println("Error deleting game: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}
