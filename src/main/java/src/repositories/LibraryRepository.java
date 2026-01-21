package src.repositories;

import src.config.Database;
import src.entities.Game;
import src.entities.Review;
import src.repositories.ReviewRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibraryRepository {

    public List<Game> getAllGamesOfUser(long userId) {
        List<Game> games = new ArrayList<>();

        String sql = """
        SELECT g.id, g.title, g.release_date, g.team,
               g.rating, g.times_listed, g.genres, g.summary
        FROM games g
        JOIN user_games ug ON g.id = ug.game_id
        WHERE ug.user_id = ?
    """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    games.add(new Game(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("release_date"),
                            rs.getString("team"),
                            rs.getFloat("rating"),
                            rs.getInt("times_listed"),
                            rs.getString("genres"),
                            rs.getString("summary")
                    ));
                }
            }

            return games;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
