package src.controllers;

import src.services.AuthService;
import src.entities.User;

import java.util.Scanner;

public class AuthController {

    private final AuthService authService = new AuthService();
    private final Scanner scanner = new Scanner(System.in);

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
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    // Login method
    public User login() {
        System.out.println("=== Login ===");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            User user = authService.login(email, password);
            return user;
        } catch (IllegalArgumentException e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }
}
