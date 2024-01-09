package com.Amadeus.FlightApi.utilities;

import com.Amadeus.FlightApi.airport.Airport;
import com.Amadeus.FlightApi.airport.AirportRepository;
import com.Amadeus.FlightApi.flight.Flight;
import com.Amadeus.FlightApi.flight.FlightRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JsonFileReader {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public JsonFileReader(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }
    @Scheduled(cron = "0 37 13 * * ?") // Execute every day at 1:26 PM
    public void readAndSaveScheduled() {
        this.readAndSave();
    }
    public void readAndSave() {
        String airportsJsonFile = "src/main/java/com/Amadeus/FlightApi/utilities/airports.json";
        String flightsJsonFile = "src/main/java/com/Amadeus/FlightApi/utilities/flights.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.findAndRegisterModules();

            // Read and save airports
            JsonNode airportsJsonNode = objectMapper.readTree(new File(airportsJsonFile));
            List<Airport> airports = convertJsonToList(airportsJsonNode, Airport.class);
            airports.forEach(this::saveAirport);

            // Read and save flights
            JsonNode flightsJsonNode = objectMapper.readTree(new File(flightsJsonFile));
            List<Flight> flights = convertJsonToList(flightsJsonNode, Flight.class);
            flights.forEach(this::saveFlight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> List<T> convertJsonToList(JsonNode jsonNode, Class<T> targetType) {
        List<T> objects = new ArrayList<>();

        for (JsonNode elementNode : jsonNode) {
            T object = createObjectFromJson(elementNode, targetType);
            objects.add(object);
        }

        return objects;
    }

    private <T> T createObjectFromJson(JsonNode jsonNode, Class<T> targetType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.treeToValue(jsonNode, targetType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }

    private void saveAirport(Airport airport) {
        Long airportId = airport.getId();
        String cityName = airport.getCityName();

        // Check if an airport with the same ID or cityName already exists
        Airport existingAirportById = airportRepository.findById(airportId).orElse(null);
        Airport existingAirportByCityName = airportRepository.findByCityName(cityName);

        if (existingAirportById != null || existingAirportByCityName != null) {
            // Handle the case when the airport already exists (e.g., update or skip)
            // For now, let's just print a message
            System.out.println("Airport with ID " + airportId + " or cityName " + cityName + " already exists. Skipping...");
        } else {
            // If the airport doesn't exist, save it
            airportRepository.save(airport);
        }
    }

    private void saveFlight(Flight flight) {
        Long flightId = flight.getId();

        // Check if a flight with the same flight ID already exists
        Optional<Flight> existingFlight = flightRepository.findFlightById(flightId);

        if (existingFlight.isPresent()) {
            // Handle the case when the flight already exists (e.g., update or skip)
            // For now, let's just print a message
            System.out.println("Flight with ID " + flightId + " already exists. Skipping...");
        } else {
            // If the flight doesn't exist, save it
            flightRepository.save(flight);
        }
    }

}
