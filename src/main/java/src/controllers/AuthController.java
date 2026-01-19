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
        boolean success = authService.register(user);

        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed: all fields must be filled.");
        }
    }

    // Login method
    public void login() {
        System.out.println("=== Login ===");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = authService.login(email, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername());
        } else {
            System.out.println("Login failed: wrong username or password.");
        }
    }
}
