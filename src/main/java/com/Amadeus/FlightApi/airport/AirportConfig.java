package com.Amadeus.FlightApi.airport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AirportConfig {
    @Bean
    CommandLineRunner airportCommandLineRunner(AirportRepository repository) {
        return args -> {
        };
    }
}
