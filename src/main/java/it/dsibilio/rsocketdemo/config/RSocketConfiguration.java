package it.dsibilio.rsocketdemo.config;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;

import java.net.URI;

@Configuration
public class RSocketConfiguration {
    @Value("${server.port:8080}")
    private int port;

    @Bean
    public Mono<RSocketRequester> rSocketRequester(
            RSocketStrategies rSocketStrategies,
            RSocketProperties rSocketProps) {
        URI socketURI = 
                URI.create(String.format("ws://localhost:%d%s", port, rSocketProps.getServer().getMappingPath()));
        
        return RSocketRequester.builder()
                .rsocketStrategies(rSocketStrategies)
                .connectWebSocket(socketURI);
    }

}