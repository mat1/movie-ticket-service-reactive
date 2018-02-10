package com.zuehlke.rating;

import com.zuehlke.movie.Rating;
import com.zuehlke.movie.rating.RatingAdapter;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class RatingAdapterIT {

    @Test
    public void getRatingsById() throws Exception {
        RatingAdapter ratingAdapter = new RatingAdapter("https://movie-rating-service.herokuapp.com");

        Flux<Rating> ratings = ratingAdapter.getRatingsById(1);

        List<Rating> results = ratings.toStream().collect(toList());

        assertThat(results, hasSize(3));
        assertThat(results, hasItem(new Rating("Internet Movie Database", "8.3/10")));
    }

}