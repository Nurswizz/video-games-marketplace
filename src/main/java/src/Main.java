package src;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


import src.config.Database;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner sc = new Scanner(System.in);

        try {
            conn = new Database().getConnection();
            System.out.println("Connected to database successfully");

            while (true) {
                System.out.println("Welcome to VideoGames Market");
                System.out.println("-----------");
                System.out.println("Write your choice");
                System.out.println("/quit");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("/quit")) {
                    System.out.println("Thanks for playing!");
                    break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}