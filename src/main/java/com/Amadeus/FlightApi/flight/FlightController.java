package com.Amadeus.FlightApi.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/flights")
public class FlightController {
    private final FlightService flightService;
    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }
    @PostMapping
    public void registerNewFlight(@RequestBody Flight flight) {
        flightService.addNewFlight(flight);
    }
    @DeleteMapping(path = "{flightId}")
    public void deleteFlight(@PathVariable("flightId") Long flightId) {
        flightService.deleteFlight(flightId);
    }
    @PutMapping(path = "{flightId}")
    public void updateFlight(@PathVariable("flightId") Long flightId,
                             @RequestParam(required = false) Double newPrice,
                             @RequestParam(required = false) Long departureAirport) {
        flightService.updateFlight(flightId, newPrice, departureAirport);
    }
}
