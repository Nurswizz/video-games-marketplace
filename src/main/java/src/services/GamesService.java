package src.services;

import src.entities.Game;
import src.entities.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import src.repositories.GameRepository;
import src.repositories.ReviewRepository;

public class GamesService {

    private final GameRepository gameRepository;
    private final ReviewRepository reviewRepository;

    public GamesService(GameRepository gameRepository,
                        ReviewRepository reviewRepository) {
        this.gameRepository = gameRepository;
        this.reviewRepository = reviewRepository;
    }

    public void addGame(Game game) {
        gameRepository.save(game);
    }

    public Game getGameById(long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Game not found with id=" + id));
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public void updateGame(Game game) {
        gameRepository.update(game);
    }

    public void deleteGame(long id) {
        gameRepository.delete(id);
    }

    public List<Game> getTopGamesByRating(int limit) {
        return gameRepository.findAll().stream()
                .sorted((a, b) -> Float.compare(b.getRating(), a.getRating()))
                .limit(limit)
                .toList();
    }

    public Game getGameWithReviews(long gameId) {
        Game game = getGameById(gameId);
        List<Review> reviews = reviewRepository.getReviewsByGameId(gameId);
        game.setReviews(reviews);
        return game;
    }
}
