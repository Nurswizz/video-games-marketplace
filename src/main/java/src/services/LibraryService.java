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

    public boolean processPurchase(User user, Game game) {
        // Check for null values
        if (user == null || game == null) {
            return false;
        }

        // Get all games current user already owns
        List<Game> myGames = libraryRepo.getAllGamesOfUser(user.getId());

        // Check for duplicates using streams
        boolean alreadyOwned = myGames.stream()
                .anyMatch(g -> Objects.equals(g.getId(), game.getId()));

        if (alreadyOwned) {
            return false;
        }

        // If not owned - add to lib
        libraryRepo.savePurchase(user.getId(), game.getId());
        return true;
    }
}