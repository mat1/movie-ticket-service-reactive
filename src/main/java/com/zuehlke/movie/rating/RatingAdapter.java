package com.zuehlke.movie.rating;

import com.zuehlke.movie.Rating;
import com.zuehlke.movie.util.WebClientFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RatingAdapter {

    private final WebClient client;

    public RatingAdapter(String url) {
        client = WebClientFactory.createWebClient(url);
    }

    public Flux<Rating> getRatingsById(long id) {
        return client.get()
                .uri("/api/v1/ratings/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, c -> Mono.empty())
                .bodyToFlux(RatingResponse.class)
                .map(Rating::from);
    }
}
