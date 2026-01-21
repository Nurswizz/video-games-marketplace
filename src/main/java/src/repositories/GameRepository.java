package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

// 1. СУЩНОСТЬ (Entity)
class Game {
    private long id;
    private final String title;
    private final String releaseDate;
    private final String team; // Исправлено: добавлена ;
    private final double rating;
    private final String timesListed;
    private final String numberOfReviews;
    private final List<String> genres;
    private final String summary;
    private final List<String> reviews;

    public Game(String title, String releaseDate, String team, double rating,
                String timesListed, String numberOfReviews, List<String> genres,
                String summary, List<String> reviews) {
        this(0, title, releaseDate, team, rating, timesListed, numberOfReviews, genres, summary, reviews);
    }

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

    // Геттеры и сеттеры
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "Game{id=" + id + ", title='" + title + "', rating=" + rating + "}";
    }
}

// 2. ИНТЕРФЕЙС РЕПОЗИТОРИЯ
interface GameRepository {
    Game save(Game game);
    Optional<Game> findById(long id);
    List<Game> findAll();
    void update(Game game);
    void delete(long id);
}

// 3. РЕАЛИЗАЦИЯ РЕПОЗИТОРИЯ (In-Memory)
class GameRepositoryImpl implements GameRepository {
    private final List<Game> games = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Game save(Game game) {
        if (game.getId() == 0) {
            game.setId(idGenerator.getAndIncrement());
        }
        games.add(game);
        return game;
    }

    @Override
    public Optional<Game> findById(long id) {
        return games.stream().filter(g -> g.getId() == id).findFirst();
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(games);
    }

    @Override
    public void update(Game updatedGame) {
        findById(updatedGame.getId()).ifPresent(existing -> {
            games.remove(existing);
            games.add(updatedGame);
        });
    }

    @Override
    public void delete(long id) {
        games.removeIf(g -> g.getId() == id);
    }
}

// 4. ГЛАВНЫЙ КЛАСС ДЛЯ ЗАПУСКА
public class Main {
    public static void main(String[] args) {
        GameRepository repository = new GameRepositoryImpl();

        // Создание игры
        Game witcher = new Game("The Witcher 3", "2015", "CD Projekt RED", 4.8,
                "1000", "500", List.of("RPG"), "Epic story", List.of("Great!"));

        // Сохранение
        repository.save(witcher);
        System.out.println("Сохранено: " + repository.findAll());

        // Поиск по ID
        repository.findById(1L).ifPresent(g -> System.out.println("Найдено по ID 1: " + g.getTitle()));

        // Удаление
        repository.delete(1L);
        System.out.println("После удаления: " + repository.findAll());
    }
}
