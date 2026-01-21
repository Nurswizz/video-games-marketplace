package src.entities;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private long id;
    private String title;
    private String releaseDate;
    private String team;
    private float rating;
    private int timesListed;
    private String genres;
    private String summary;
    private List<Review> reviews = new ArrayList<>();

    // Constructor for loading from DB
    public Game(long id, String title, String releaseDate, String team,
                float rating, int timesListed, String genres, String summary) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.team = team;
        this.rating = rating;
        this.timesListed = timesListed;
        this.genres = genres;
        this.summary = summary;
    }

    // Constructor for creating new game
    public Game(String title, String releaseDate, String team,
                float rating, int timesListed, String genres, String summary) {
        this(0, title, releaseDate, team, rating, timesListed, genres, summary);
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getReleaseDate() { return releaseDate; }
    public String getTeam() { return team; }
    public float getRating() { return rating; }
    public int getTimesListed() { return timesListed; }
    public String getGenres() { return genres; }
    public String getSummary() { return summary; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
