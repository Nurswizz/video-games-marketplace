package src.entities;

public class Review {
    private long id;
    private String description;
    private long game_id;

    public Review(long id, String description, long game_id) {
        this.id = id;
        this.description = description;
        this.game_id = game_id;
    }

    public Review(String description, long game_id) {
        this(0,description,game_id);
    }

    public long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public long getGame_id() {
        return game_id;
    }
}
