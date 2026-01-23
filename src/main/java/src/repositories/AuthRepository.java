package src.repositories;

import src.entities.Game;
import src.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import src.config.Database;

public class AuthRepository {

    public void save(User user) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement stt = conn.prepareStatement("INSERT INTO users (username, password, email) VALUES (?,?,?)");
            stt.setString(1, user.getUsername());
            stt.setString(2, user.getPassword());
            stt.setString(3, user.getEmail());
            stt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {

            stt.setString(1, email);

            try (ResultSet rs = stt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getBoolean("is_admin")
                    );
                }
            }

            return null; // user not found

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user by email", e);
        }
    }
}





