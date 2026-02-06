package src.interfaces;

import src.entities.Game;

import java.util.List;

public interface IGamesService {
    void addGame(Game game);
    Game getGameById(long id);
    List<Game> getAllGames();
    void updateGame(Game game);
    void deleteGame(long id);
    List<Game> getTopGamesByRating(int limit);
    Game getGameWithReviews(long gameId);
    List<Game> searchGames(String query);
}
