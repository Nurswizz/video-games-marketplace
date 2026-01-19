package src;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


import src.config.Database;
import src.controllers.AuthController;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner sc = new Scanner(System.in);
        AuthController authController = new AuthController();

        try {
            System.out.println("1. Register");
            System.out.println("2. Login");

            String choice =  sc.nextLine();
            if (choice.equals("1")) {
                authController.register();
            } else if (choice.equals("2")) {
                authController.login();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}