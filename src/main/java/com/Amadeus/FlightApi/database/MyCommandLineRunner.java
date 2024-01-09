package com.Amadeus.FlightApi.database;
import com.Amadeus.FlightApi.airport.AirportRepository;
import com.Amadeus.FlightApi.flight.FlightRepository;
import com.Amadeus.FlightApi.utilities.JsonFileReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public MyCommandLineRunner(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            JsonFileReader jsonFileReader = new JsonFileReader(airportRepository, flightRepository);
            jsonFileReader.readAndSaveScheduled();
        };
    }
}
