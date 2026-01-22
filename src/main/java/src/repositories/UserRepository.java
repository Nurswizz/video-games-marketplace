package src.repositories;

import src.config.Database;
import src.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {

    public boolean updateBalance(User user) {
        String sql = "UPDATE users SET balance = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, user.getBalance());
            stmt.setLong(2, user.getId());

            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating user balance", e);
        }
    }
}
