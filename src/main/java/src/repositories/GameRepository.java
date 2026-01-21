package src.repositories;

<<<<<<< HEAD
import src.config.Database;
import java.sql.*;
=======
>>>>>>> f1ab564079477abc0d8ab6d5ee4e8db259ee7fb2
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import src.entities.Game;

public class GameRepository {

<<<<<<< HEAD
    // Вспомогательный класс сущности прямо внутри (как просили)
    public static class Game {
        private long id;
        private final String title;
        private final String releaseDate;
        private final String team;
        private final double rating;
        private final String timesListed;
        private final String numberOfReviews;
        private final List<String> genres;
        private final String summary;
        private final List<String> reviews;

        // Конструктор для загрузки из БД
        public Game(long id, String title, String releaseDate, String team, double rating,
                    String timesListed, String numberOfReviews, List<String> genres,
                    String summary, List<String> reviews) {
            this.id = id;
            this.title = title;
            this.releaseDate = releaseDate;
            this.team = team;
            this.rating = rating;
            this.timesListed = timesListed;
            this.numberOfReviews = numberOfReviews;
            this.genres = genres;
            this.summary = summary;
            this.reviews = reviews;
        }

        // Геттеры
        public long getId() { return id; }
        public String getTitle() { return title; }
        public String getReleaseDate() { return releaseDate; }
        public String getTeam() { return team; }
        public double getRating() { return rating; }
        public String getTimesListed() { return timesListed; }
        public String getNumberOfReviews() { return numberOfReviews; }
        public List<String> getGenres() { return genres; }
        public String getSummary() { return summary; }
        public List<String> getReviews() { return reviews; }
    }

    // --- МЕТОДЫ РЕПОЗИТОРИЯ ---

    public void save(Game game) {
        String sql = "INSERT INTO games (title, release_date, team, rating, times_listed, number_of_reviews, summary) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {
            stt.setString(1, game.getTitle());
            stt.setString(2, game.getReleaseDate());
            stt.setString(3, game.getTeam());
            stt.setDouble(4, game.getRating());
            stt.setString(5, game.getTimesListed());
            stt.setString(6, game.getNumberOfReviews());
            stt.setString(7, game.getSummary());
            stt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении игры", e);
        }
    }

    public Optional<Game> findById(long id) {
        String sql = "SELECT * FROM games WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {
            stt.setLong(1, id);
            try (ResultSet rs = stt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToGame(rs));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске по ID", e);
        }
        return Optional.empty();
    }

    public List<Game> findAll() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games";
        try (Connection conn = Database.getConnection();
             Statement stt = conn.createStatement();
             ResultSet rs = stt.executeQuery(sql)) {
            while (rs.next()) {
                games.add(mapResultSetToGame(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении всех игр", e);
        }
        return games;
    }

    public void update(Game game) {
        String sql = "UPDATE games SET title=?, rating=?, summary=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {
            stt.setString(1, game.getTitle());
            stt.setDouble(2, game.getRating());
            stt.setString(3, game.getSummary());
            stt.setLong(4, game.getId());
            stt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при обновлении игры", e);
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM games WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stt = conn.prepareStatement(sql)) {
            stt.setLong(1, id);
            stt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении игры", e);
        }
    }

    // Приватный метод, чтобы не дублировать код создания объекта из базы
    private Game mapResultSetToGame(ResultSet rs) throws SQLException {
        return new Game(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("release_date"),
                rs.getString("team"),
                rs.getDouble("rating"),
                rs.getString("times_listed"),
                rs.getString("number_of_reviews"),
                new ArrayList<>(), // Жанры обычно хранятся в отдельной таблице
                rs.getString("summary"),
                new ArrayList<>()  // Отзывы тоже в отдельной таблице
        );
    }
=======
    // Список для хранения данных прямо внутри класса
    private final List<Game> games = new ArrayList<>();
    private long nextId = 1;

    // 1. Метод Save
    public Game save(Game game) {
        if (game.getId() == 0) {
            game.setId(nextId++);
        }
        games.add(game);
        return game;
    }

    // 2. Метод FindById
    public Optional<Game> findById(long id) {
        return games.stream()
                .filter(g -> g.getId() == id)
                .findFirst();
    }

    // 3. Метод FindAll
    public List<Game> findAll() {
        return new ArrayList<>(games);
    }

    // 4. Метод Update
    public void update(Game updatedGame) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == updatedGame.getId()) {
                games.set(i, updatedGame);
                return;
            }
        }
    }

    // 5. Метод Delete
    public void delete(long id) {
        games.removeIf(g -> g.getId() == id);
    }


>>>>>>> f1ab564079477abc0d8ab6d5ee4e8db259ee7fb2
}