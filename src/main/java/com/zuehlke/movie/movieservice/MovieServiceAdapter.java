package com.zuehlke.movie.movieservice;

import com.zuehlke.movie.Movie;
import com.zuehlke.movie.MovieDetail;
import com.zuehlke.movie.util.WebClientFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MovieServiceAdapter {

    private final WebClient client;

    public MovieServiceAdapter(String url) {
        client = WebClientFactory.createWebClient(url);
    }

    public Flux<Movie> getAll() {
        return client.get()
                .uri("/api/v1/movies")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(MovieServiceResponse.class)
                .map(Movie::from);
    }

    public Mono<MovieDetail> getMovieById(long id) {
        return client.get()
                .uri("/api/v1/movies/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, c -> Mono.empty())
                .bodyToMono(MovieServiceResponse.class)
                .map(MovieDetail::from);
    }

}
