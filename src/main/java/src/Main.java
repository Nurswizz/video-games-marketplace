package src;

import src.entities.User;

import java.util.Scanner;
import src.controllers.AuthController;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthController authController = new AuthController();

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    authController.register();
                    break;
                case "2":
                    authController.login();
                    break;
                case "3":
                    System.exit(0);
                    break;
            }
        }
    }
}