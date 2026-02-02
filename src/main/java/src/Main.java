package src;

import src.controllers.AuthController;
import src.repositories.GameRepository;
import src.repositories.LibraryRepository;
import src.repositories.ReviewRepository;
import src.services.GamesService;
import src.controllers.GamesController;
import src.services.LibraryService;

public class Main {

    private static AuthController authController;
    private static GamesController controller;

    public static void init() {
        authController = new AuthController();
        GameRepository gameRepo = new GameRepository();
        ReviewRepository reviewRepo = new ReviewRepository();
        LibraryRepository libraryRepo = new LibraryRepository();

        GamesService gamesService = new GamesService(gameRepo, reviewRepo);
        LibraryService libraryService = new LibraryService(libraryRepo);
        controller = new GamesController(gamesService, libraryService);

    }
    public static void main(String[] args) {
        init();

        authController.authenticateUser();

        controller.start();
    }

}