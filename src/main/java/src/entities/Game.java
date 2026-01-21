package src.entities;

import java.util.List;

public class Game {

    private long id;
    private final String title;
    private final String releaseDate;
    private final String team
    private final double rating;
    private final String timesListed;
    private final String numberOfReviews;
    private final List<String> genres;
    private final String summary;
    private final List<String> reviews;

    // Для создания новой игры
    public Game(String title, String releaseDate, String team, double rating,
                String timesListed, String numberOfReviews, List<String> genres,
                String summary, List<String> reviews) {
        this(0, title, releaseDate, team, rating, timesListed, numberOfReviews, genres, summary, reviews);
    }

    // Основное, используется при загрузке из базы
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTeam() {
        return team;
    }

    public double getRating() {
        return rating;
    }

    public String getTimesListed() {
        return timesListed;
    }

    public String getNumberOfReviews() {
        return numberOfReviews;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getSummary() {
        return summary;
    }

    public List<String> getReviews() {
        return reviews;
    }
}