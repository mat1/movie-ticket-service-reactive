package com.zuehlke.movie.movieservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieServiceResponse {

    private final long id;
    private final String title;
    private final String poster;
    private final String plot;
    private final int year;
    private final String genre;

    @JsonCreator
    public MovieServiceResponse(@JsonProperty("id") long id,
                                @JsonProperty("Title") String title,
                                @JsonProperty("Poster") String poster,
                                @JsonProperty("Plot") String plot,
                                @JsonProperty("Year") int year,
                                @JsonProperty("Genre") String genre) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.plot = plot;
        this.year = year;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getPlot() {
        return plot;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }
}
