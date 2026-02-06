package src.services;

import src.interfaces.IAuthService;
import src.interfaces.IGamesService;
import src.repositories.AuthRepository;
import src.entities.User;
import src.utils.Validation;

public class AuthService implements IAuthService {

    private final AuthRepository auth = new AuthRepository();

    public void register(User user) {

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled.");
        }

        User doesExist = auth.getUserByEmail(user.getEmail());
        if (doesExist != null) {
            throw new IllegalArgumentException("User with this email already exists.");
        }

        if (!Validation.validateEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        auth.save(user);
    }

    public User login(String email,  String password) {
        if (email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty.");
        }

        User user = auth.getUserByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

        return user;
    }

}
