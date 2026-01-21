package src.repositories;

import src.config.Database;
import src.entities.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReviewRepository {

    public ArrayList<Review> getReviewsByGameId(long gameId) {
        String sql = """
            SELECT id, description, game_id
            FROM reviews
            WHERE game_id = ?
        """;

        ArrayList<Review> reviews = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, gameId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reviews.add(new Review(
                            rs.getLong("id"),
                            rs.getString("description"),
                            rs.getLong("game_id")
                    ));
                }
            }

            return reviews;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load reviews for game " + gameId, e);
        }
    }
}
