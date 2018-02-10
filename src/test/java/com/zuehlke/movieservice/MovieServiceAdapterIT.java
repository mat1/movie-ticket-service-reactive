package com.zuehlke.movieservice;

import com.zuehlke.movie.Movie;
import com.zuehlke.movie.MovieDetail;
import com.zuehlke.movie.movieservice.MovieServiceAdapter;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class MovieServiceAdapterIT {

    @Test
    public void getAll() throws Exception {
        MovieServiceAdapter movieServiceAdapter = new MovieServiceAdapter("https://movie-service.herokuapp.com/");

        Flux<Movie> movies = movieServiceAdapter.getAll();
        List<Movie> result = movies.toStream().collect(toList());

        assertThat(result, hasSize(7));
        assertThat(result, hasItem(new Movie(1, "Batman Begins", "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg")));
    }

    @Test
    public void getMovieById() throws Exception {
        MovieServiceAdapter movieServiceAdapter = new MovieServiceAdapter("https://movie-service.herokuapp.com/");

        Mono<MovieDetail> movieDetail = movieServiceAdapter.getMovieById(1);

        MovieDetail result = movieDetail.block();

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void getMovieById_NotExistingId() throws Exception {
        MovieServiceAdapter movieServiceAdapter = new MovieServiceAdapter("https://movie-service.herokuapp.com/");

        Mono<MovieDetail> movieDetail = movieServiceAdapter.getMovieById(1337);

        MovieDetail result = movieDetail.block();

        assertThat(result, is(nullValue()));
    }


}