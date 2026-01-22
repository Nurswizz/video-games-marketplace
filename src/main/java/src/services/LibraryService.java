package src.services;

import src.entities.Game;
import src.entities.User;
import src.repositories.LibraryRepository;
import java.util.List;
import java.util.Objects;

public class LibraryService {
    private final LibraryRepository libraryRepo;

    public LibraryService(LibraryRepository libraryRepo) {
        this.libraryRepo = libraryRepo;
    }

    public boolean processPurchase(long userId, long gameId) {

        // Get all games current user already owns
        List<Game> myGames = libraryRepo.getAllGamesOfUser(userId);

        // Check for duplicates using streams
        boolean alreadyOwned = myGames.stream()
                .anyMatch(g -> Objects.equals(g.getId(), gameId));

        if (alreadyOwned) {
            return false;
        }

        libraryRepo.savePurchase(userId, gameId);
        return true;
    }

    public List<Game> getAllGamesOfUser(long userId) {
        return libraryRepo.getAllGamesOfUser(userId);
    }
}