package src.controllers;

import src.entities.Game;
import src.services.GamesService;
import src.services.LibraryService;

import src.utils.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GamesController {

    private final GamesService gamesService;
    private final LibraryService libraryService;
    private final Scanner scanner = new Scanner(System.in);

    public GamesController(GamesService gamesService, LibraryService libraryService) {
        this.gamesService = gamesService;
        this.libraryService = libraryService;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> showAllGames();
                case "2" -> showTopGames();
                case "3" -> showGameDetails();
                case "4" -> addGame();
                case "5" -> showLibrary();
                case "6" -> searchGame();
                case "0" -> running = false;
                default -> System.out.println("Unknown command");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Games Menu ===");
        System.out.println("1. List all games");
        System.out.println("2. Top games by rating");
        System.out.println("3. Game details");
        System.out.println("4. Add game");
        System.out.println("5. Library");
        System.out.println("6. Search game");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    private void showAllGames() {
        List<Game> games = gamesService.getAllGames();
        games.forEach(this::printShort);
    }

    private void showTopGames() {
        System.out.print("Enter limit: ");
        int limit = Integer.parseInt(scanner.nextLine());

        gamesService.getTopGamesByRating(limit)
                .forEach(this::printShort);
    }

    private void showGameDetails() {
        System.out.print("Enter game id: ");
        long gameId = Long.parseLong(scanner.nextLine());

        Game game = gamesService.getGameWithReviews(gameId);

        boolean inDetails = true;
        while (inDetails) {
            printGameDetails(game);
            printGameActions();

            String cmd = scanner.nextLine();
            switch (cmd) {
                case "1" -> addToLibrary(game);
                case "0" -> inDetails = false;
                default -> System.out.println("Unknown command");
            }
        }
    }

    private void printGameDetails(Game game) {
        System.out.println("\n=== Game Details ===");
        System.out.println("Title: " + game.getTitle());
        System.out.println("Rating: " + game.getRating());
        System.out.println("Genres: " + game.getGenres());
        System.out.println("Summary: " + game.getSummary());
        System.out.println("Reviews: " + game.getReviews().size());
        System.out.println("ID: " + game.getId());
    }

    private void printGameActions() {
        System.out.println("\n1. Add to library");
        System.out.println("0. Back");
        System.out.print("Choose option: ");
    }

    private void addToLibrary(Game game) {
        long userId = Session.getId(); // используем текущего пользователя из сессии
        try {
            libraryService.processPurchase(userId, game.getId());
            System.out.println("Game added to your library.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showLibrary() {
        long userId = Session.getId();

        try {
            List<Game> games = libraryService.getAllGamesOfUser(userId);

            for (Game g : games) {
                printGameDetails(g);
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addGame() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Release date: ");
        String releaseDate = scanner.nextLine();

        System.out.print("Team: ");
        String team = scanner.nextLine();

        System.out.print("Rating: ");
        float rating = Float.parseFloat(scanner.nextLine());

        System.out.print("Times listed: ");
        int timesListed = Integer.parseInt(scanner.nextLine());

        System.out.print("Genres: ");
        String genres = scanner.nextLine();

        System.out.print("Summary: ");
        String summary = scanner.nextLine();

        Game game = new Game(
                title,
                releaseDate,
                team,
                rating,
                timesListed,
                genres,
                summary
        );

        gamesService.addGame(game);
        System.out.println("Game added.");
    }

    private void searchGame() {
        System.out.println("\n=== Search Game ===");
        System.out.println("Enter game name: ");
        String query = scanner.nextLine();

        List<Game> games = new ArrayList<>();

        games = gamesService.searchGames(query);
        if (games.isEmpty()) {
            System.out.println("Game not found.");
            return;
        }
        for (Game g : games) {
            printGameDetails(g);
        }

    }

    private void printShort(Game game) {
        System.out.println(
                game.getId() + " | " +
                        game.getTitle() + " | rating: " +
                        game.getRating()
        );
    }
}
