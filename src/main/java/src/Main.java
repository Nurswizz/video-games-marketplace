package src;

import src.entities.User;

import java.util.Scanner;
import src.controllers.AuthController;
import src.repositories.GameRepository;
import src.repositories.LibraryRepository;
import  src.repositories.ReviewRepository;
import src.services.GamesService;
import src.controllers.AuthController;
import src.controllers.GamesController;
import src.services.LibraryService;

import src.controllers.AuthController;


public class Main {
    public static void main(String[] args) {

        AuthController authController = new AuthController();

        authController.authenticateUser();

        GameRepository gameRepo = new GameRepository();
        ReviewRepository reviewRepo = new ReviewRepository();
        LibraryRepository libraryRepo = new LibraryRepository();

        GamesService gamesService = new GamesService(gameRepo, reviewRepo);
        LibraryService libraryService = new LibraryService(libraryRepo);
        GamesController controller = new GamesController(gamesService,  libraryService);
        controller.start();
    }

}