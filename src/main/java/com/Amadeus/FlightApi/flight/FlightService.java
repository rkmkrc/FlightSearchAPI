package com.Amadeus.FlightApi.flight;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }
    public Flight getFlightById(Long flightId) {
        Optional<Flight> optionalFlight = flightRepository.findFlightById(flightId);
        if (optionalFlight.isPresent()) {
            return optionalFlight.get();
        } else {
            throw new IllegalStateException("Flight with id = " + flightId + " does not exist.");
        }
    }
    public List<Flight> getFlightsWithAirportsAndDepartureDate(Long departureAirportId, Long arrivalAirportId, String departureDateTime) {
        Optional<List<Flight>> optionalFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTime(departureAirportId, arrivalAirportId, departureDateTime);
        if (optionalFlights.isPresent()) {
            return optionalFlights.get();
        } else {
            throw new IllegalStateException("There is no flights found for given parameters.");
        }
    }
    public void addNewFlight(Flight flight) {
        Optional<Flight> optionalFlight = flightRepository.findFlightById(flight.getId());
        if (optionalFlight.isPresent()) {
            throw new IllegalStateException("Flight id = " + flight.getId() +" already exists.");
        }
        flightRepository.save(flight);
    }
    public void deleteFlight(Long flightId) {
        boolean exists = flightRepository.existsById(flightId);
        if (!exists) {
            throw new IllegalStateException("Flight id = " + flightId +" does not exists.");
        }
        flightRepository.deleteById(flightId);
    }
    @Transactional
    public void updateFlight(Long flightId, Double newPrice, Long departureAirport) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() ->
                new IllegalStateException("Flight id = %d does not exists.".formatted(flightId)));
        if (newPrice > 0 && !Objects.equals(flight.getPrice(), newPrice)) {
            flight.setPrice(newPrice);
        }
        if (departureAirport != null) {
            flight.setDepartureAirportId(departureAirport);
        }
    }
}
