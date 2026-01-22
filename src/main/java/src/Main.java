package src;

import src.entities.User;

import java.util.Scanner;
import src.controllers.AuthController;
import src.repositories.GameRepository;
import  src.repositories.ReviewRepository;
import src.services.GamesService;
import src.controllers.AuthController;
import src.controllers.GamesController;


public class Main {
    public static void main(String[] args) {

        GameRepository gameRepo = new GameRepository();
        ReviewRepository reviewRepo = new ReviewRepository();

        GamesService gamesService = new GamesService(gameRepo, reviewRepo);
        GamesController controller = new GamesController(gamesService);

        controller.start();
    }

}