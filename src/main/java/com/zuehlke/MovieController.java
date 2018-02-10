package com.zuehlke;

import com.zuehlke.movie.Movie;
import com.zuehlke.movie.MovieDetail;
import com.zuehlke.movie.Rating;
import com.zuehlke.movie.movieservice.MovieServiceAdapter;
import com.zuehlke.movie.rating.RatingAdapter;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "http://localhost:8001")
@RestController
@RequestMapping("/api/v1")
public class MovieController {

    private final MovieServiceAdapter movieServiceAdapter;
    private final RatingAdapter ratingAdapter;

    public MovieController(MovieServiceAdapter movieServiceAdapter, RatingAdapter ratingAdapter) {
        this.movieServiceAdapter = movieServiceAdapter;
        this.ratingAdapter = ratingAdapter;
    }

    @GetMapping("/movies")
    public Flux<Movie> getMovies() {
        return movieServiceAdapter.getAll();
    }

    @GetMapping("/movies/{id}")
    public Mono<MovieDetail> getMovieById(@PathVariable("id") long id) {
        Mono<MovieDetail> movieDetailMono = movieServiceAdapter.getMovieById(id);
        Flux<Rating> ratingsFlux = ratingAdapter.getRatingsById(id);

        return movieDetailMono.map(movieDetail -> {
            List<Rating> ratings = ratingsFlux.toStream().collect(toList());
            movieDetail.setRatings(ratings);
            return movieDetail;
        });
    }
}
