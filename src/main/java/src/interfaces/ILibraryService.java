package src.interfaces;

import src.entities.Game;

import java.util.List;

public interface ILibraryService {
    boolean processPurchase(long userId, long gameId);
    List<Game> getAllGamesOfUser(long userId);
}
