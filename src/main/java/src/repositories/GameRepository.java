package src.repositories;

import src.entities.Game;
import src.config.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepository {

    // 1. Сохранение новой игры
    public void save(Game game) {
        String sql = "INSERT INTO video_games (title, release_date, team, rating, times_listed, number_of_reviews, summary) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {

            stt.setString(1, game.getTitle());
            stt.setString(2, game.getReleaseDate());
            stt.setString(3, game.getTeam());
            stt.setDouble(4, game.getRating());
            stt.setInt(5, game.getTimesListed());
            stt.setString(6, game.getSummary());

            stt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error saving game", e);
        }
    }

    // 2. Поиск по ID
    public Optional<Game> findById(long id) {
        String sql = "SELECT * FROM video_games WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {

            stt.setLong(1, id);

            try (ResultSet rs = stt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToGame(rs));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding game by id", e);
        }
        return Optional.empty();
    }


    // 3. Получение всех игр
    public List<Game> findAll() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM video_games";

        try (Connection conn = Database.getConnection();
             Statement stt = conn.createStatement();
             ResultSet rs = stt.executeQuery(sql)) {

            while (rs.next()) {
                games.add(mapResultSetToGame(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all games", e);
        }
        return games;
    }

    // 4. Обновление данных игры
    public void update(Game game) {
        String sql = "UPDATE video_games SET title=?, release_date=?, team=?, rating=?, times_listed=?, number_of_reviews=?, summary=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {

            stt.setString(1, game.getTitle());
            stt.setString(2, game.getReleaseDate());
            stt.setString(3, game.getTeam());
            stt.setDouble(4, game.getRating());
            stt.setInt(5, game.getTimesListed());
            stt.setString(6, game.getSummary());
            stt.setLong(7, game.getId());

            stt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error updating game", e);
        }
    }

    // 5. Удаление игры
    public void delete(long id) {
        String sql = "DELETE FROM video_games WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {

            stt.setLong(1, id);
            stt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error deleting game", e);
        }
    }

    // Вспомогательный метод для маппинга строк из БД в объект Game
    private Game mapResultSetToGame(ResultSet rs) throws SQLException {
        return new Game(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("release_date"),
                rs.getString("team"),
                rs.getFloat("rating"),
                rs.getInt("times_listed"),
                rs.getString("genres"),// Жанры (List<String>)
                rs.getString("summary")
        );
    }

    public List<Game> searchSmart(String keyword) {
        if (keyword == null) return findAll();

        String k = keyword.trim().toLowerCase();

        return findAll().stream()
                .filter(g -> g.getTitle().toLowerCase().replaceAll("\\s+", "")
                        .contains(k.replaceAll("\\s+", ""))).limit(10)
                .toList();
    }
}
