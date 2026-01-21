import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // Вспомогательный класс Game (можно оставить в этом же файле внизу)
    public static class Game {
        private long id;
        private String title;
        private double rating;

        public Game(String title, double rating) {
            this.title = title;
            this.rating = rating;
        }

        public long getId() { return id; }
        public void setId(long id) { this.id = id; }
        public String getTitle() { return title; }

        @Override
        public String toString() {
            return "Game{id=" + id + ", title='" + title + "', rating=" + rating + "}";
        }
    }

    // Точка входа для проверки
    public static void main(String[] args) {
        GameRepository repository = new GameRepository();

        // Создаем и сохраняем
        Game g1 = new Game("GTA V", 4.5);
        repository.save(g1);

        // Выводим все
        System.out.println("Все игры: " + repository.findAll());

        // Удаляем
        repository.delete(1);
        System.out.println("После удаления: " + repository.findAll());
    }
}