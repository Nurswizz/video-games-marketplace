package src.controllers;

import src.services.AuthService;
import src.entities.User;

import java.util.Scanner;

import src.utils.Session;

public class AuthController {

    private final AuthService authService = new AuthService();
    private final Scanner scanner = new Scanner(System.in);

    public void authenticateUser() {
        System.out.println("Authorization");
        System.out.println("===============");
        System.out.println("1. Register");
        System.out.println("2. Login");

        String choice =  scanner.nextLine();

        if (choice.equals("1")) {
            register();
        } else  if (choice.equals("2")) {
            login();
        } else {
            System.out.println("Wrong choice");
            authenticateUser();
        }
    }

    // Registration method
    public void register() {
        System.out.println("=== Register ===");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = new User(username, password, email);
        try {
            authService.register(user);
            System.out.println("Registration successful!");
            Session.login(user.getId(), user.isAdmin());
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    // Login method
    public void login() {
        System.out.println("=== Login ===");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            User user = authService.login(email, password);
            System.out.println("Login successful!");
            Session.login(user.getId(), user.isAdmin());
        } catch (IllegalArgumentException e) {
            System.out.println("Login failed: " + e.getMessage());

        }
    }
}
