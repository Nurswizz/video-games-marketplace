package src.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import src.entities.Game;

public class GameRepository {

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


}