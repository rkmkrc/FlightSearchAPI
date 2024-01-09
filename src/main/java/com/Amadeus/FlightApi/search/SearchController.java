package com.Amadeus.FlightApi.search;

import com.Amadeus.FlightApi.airport.Airport;
import com.Amadeus.FlightApi.airport.AirportService;
import com.Amadeus.FlightApi.flight.Flight;
import com.Amadeus.FlightApi.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/search")
public class SearchController {
    private final AirportService airportService;
    private final FlightService flightService;
    @Autowired
    public SearchController(AirportService airportService, FlightService flightService) {this.airportService = airportService; this.flightService = flightService;}
    @GetMapping
    public List<Flight> getAllFlights() {return flightService.getFlights();}
    @GetMapping(path = "/flights/{flightId}")
    public Flight getFlightById(@PathVariable("flightId") Long flightId) {
        return flightService.getFlightById(flightId);
    }
    @GetMapping(path = "/airports/{airportId}")
    public Airport getAirportById(@PathVariable("airportId") Long airportId) {
        return airportService.getAirportWithId(airportId);
    }
    @GetMapping("/flights/{departureAirportId}/{arrivalAirportId}/{departureDate}")
    public ResponseEntity<List<Flight>> getFlightsByAirportsAndDepartureDate(
            @PathVariable Long departureAirportId,
            @PathVariable Long arrivalAirportId,
            @PathVariable String departureDate,
            @RequestParam(required = false) String returnDate) {

        List<Flight> flights;

        if (returnDate == null) {
            // One-way flight
            flights = flightService.getFlightsWithAirportsAndDepartureDate(
                    departureAirportId, arrivalAirportId, departureDate);
        } else {
            // Round-trip flight
            List<Flight> departingFlights = flightService.getFlightsWithAirportsAndDepartureDate(
                    departureAirportId, arrivalAirportId, departureDate);

            List<Flight> returningFlights = flightService.getFlightsWithAirportsAndDepartureDate(
                    arrivalAirportId, departureAirportId, returnDate);

            // Combine both lists
            departingFlights.addAll(returningFlights);
            flights = departingFlights;
        }

        return ResponseEntity.ok(flights);
    }


}
