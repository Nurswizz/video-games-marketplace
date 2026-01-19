package src.repositories;

import src.entities.User;

import java.sql.Connection;
import java.sql.Statement;

public class AuthRepository {

    Connection con;

    public AuthRepository(Connection con) {
        this.con = con;
    }

    public void register(User user) {
        try {
            // TODO: implementation of registration logic of the user
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void login(String username, String password) {
        try {
            // TODO: implemenation of login logic of the user

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
