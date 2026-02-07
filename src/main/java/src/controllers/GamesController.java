package src.controllers;

import src.entities.Game;
import src.interfaces.IGamesService;
import src.interfaces.ILibraryService;
import src.services.GamesService;
import src.services.LibraryService;
import src.utils.InputReader;
import src.utils.Session;
import src.utils.Validation;
import src.utils.AccessManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GamesController {

    private final IGamesService gamesService;
    private final ILibraryService libraryService;
    private final InputReader inputReader;
    private final AdminPanelController adminPanelController;

    public GamesController(IGamesService gamesService, ILibraryService libraryService) {
        this.gamesService = gamesService;
        this.libraryService = libraryService;
        this.inputReader = new InputReader(new Scanner(System.in));
        this.adminPanelController = new AdminPanelController(gamesService);
    }


    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String command = inputReader.readLine();

            switch (command) {
                case "1" -> showAllGames();
                case "2" -> showTopGames();
                case "3" -> showGameDetails();
                case "4" -> showLibrary();
                case "5" -> searchGame();
                case "9" -> handleAdminAccess();
                case "0" -> running = false;
                default -> System.out.println("Unknown command. Please try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== Games Menu ===");
        System.out.println("1. List all games");
        System.out.println("2. Top games by rating");
        System.out.println("3. Game details");
        System.out.println("4. Library");
        System.out.println("5. Search game");
        if (Session.getInstance().isAdmin()) {
            System.out.println("9. Admin panel");
        }
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    private void handleAdminAccess() {
        try {
            AccessManager.requireAdmin();
            adminPanelController.showAdminPanel();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
    }


    private void showAllGames() {
        System.out.print("Enter limit:");
        Integer number = inputReader.readInteger();
        if (number == null || number <= 0) {
            System.out.println("Invalid limit.");
            return;
        }

        List<Game> allGames = gamesService.getAllGames();
        number = Math.min(number, allGames.size());

        allGames.subList(0, number).forEach(this::printGameSummary);
    }

    private void showTopGames() {
        System.out.print("Enter limit: ");
        Integer limit = inputReader.readInteger();
        if (limit == null || !Validation.isPositive(limit)) {
            System.out.println("Invalid limit. Please enter a positive number.");
            return;
        }

        List<Game> topGames = gamesService.getTopGamesByRating(limit);
        if (topGames.isEmpty()) {
            System.out.println("No games found.");
            return;
        }
        topGames.forEach(this::printGameSummary);
    }

    private void showGameDetails() {
        System.out.print("Enter game ID: ");
        Long gameId = inputReader.readLong();
        if (gameId == null || !Validation.isValidId(gameId)) {
            System.out.println("Invalid game ID.");
            return;
        }

        try {
            Game game = gamesService.getGameWithReviews(gameId);
            gameDetailsLoop(game);
        } catch (IllegalArgumentException e) {
            System.out.println("Game not found: " + e.getMessage());
        }
    }

    private void gameDetailsLoop(Game game) {
        boolean inDetails = true;
        while (inDetails) {
            printFullGameDetails(game);
            printGameActionsMenu();

            String command = inputReader.readLine();
            switch (command) {
                case "1" -> addToLibrary(game);
                case "0" -> inDetails = false;
                default -> System.out.println("Unknown command.");
            }
        }
    }

    private void printFullGameDetails(Game game) {
        System.out.println("\n=== Game Details ===");
        System.out.println("ID: " + game.getId());
        System.out.println("Title: " + game.getTitle());
        System.out.println("Rating: " + String.format("%.1f", game.getRating()));
        System.out.println("Genres: " + game.getGenres());
        System.out.println("Summary: " + game.getSummary());
        System.out.println("Reviews: " + game.getReviews().size());
    }

    private void printGameActionsMenu() {
        System.out.println("\n1. Add to library");
        System.out.println("0. Back");
        System.out.print("Choose option: ");
    }

    private void addToLibrary(Game game) {
        long userId = Session.getInstance().getUserId();
        try {
            libraryService.processPurchase(userId, game.getId());
            System.out.println("Game successfully added to your library.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showLibrary() {
        long userId = Session.getInstance().getUserId();

        try {
            List<Game> games = libraryService.getAllGamesOfUser(userId);
            if (games.isEmpty()) {
                System.out.println("Your library is empty.");
                return;
            }
            System.out.println("\n=== Your Library ===");
            games.forEach(this::printFullGameDetails);
        } catch (IllegalStateException e) {
            System.out.println("Error loading library: " + e.getMessage());
        }
    }

    private void searchGame() {
        System.out.println("\n=== Search Game ===");
        System.out.print("Enter game name: ");
        String query = inputReader.readLine();

        if (!Validation.isNotEmpty(query)) {
            System.out.println("Search query cannot be empty.");
            return;
        }

        List<Game> games = gamesService.searchGames(query);
        if (games.isEmpty()) {
            System.out.println("No games found matching '" + query + "'.");
            return;
        }

        System.out.println("\n=== Search Results ===");
        games.forEach(this::printFullGameDetails);
    }

    private void printGameSummary(Game game) {
        System.out.printf("%d | %s | Rating: %.1f%n",
                game.getId(),
                game.getTitle(),
                game.getRating());
    }
}