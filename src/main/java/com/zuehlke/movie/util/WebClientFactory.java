package com.zuehlke.movie.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientFactory {

    private final static Logger logger = LoggerFactory.getLogger(WebClientFactory.class);

    public static WebClient createWebClient(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .filter(logRequest())
                .build();
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

}
