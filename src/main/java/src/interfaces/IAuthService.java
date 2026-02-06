package src.interfaces;

import src.entities.User;

public interface IAuthService {
    void register(User user);
    User login(String email,  String password);
}
