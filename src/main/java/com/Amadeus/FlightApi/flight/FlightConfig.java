package com.Amadeus.FlightApi.flight;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlightConfig {

    @Bean
    CommandLineRunner flightCommandLineRunner(FlightRepository repository) {
        return args -> {
        };
    }
}
