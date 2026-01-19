package src.services;

import src.repositories.AuthRepository;
import src.entities.User;

public class AuthService {

    private final AuthRepository auth = new AuthRepository();

    public boolean register(User user) {

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            return false;
        }

        User doesExist = auth.getUserByEmail(user.getEmail());
        if (doesExist != null) {
            return false;
        }

        auth.save(user);

        return true;
    }

    public User login(String email,  String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return null;
        }

        User user = auth.getUserByEmail(email);

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }

        return null;


    }

}
