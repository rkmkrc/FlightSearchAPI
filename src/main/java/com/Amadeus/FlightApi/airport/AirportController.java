package com.Amadeus.FlightApi.airport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(path = "api/v1/airports")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAirports() {
        List<Airport> airports = airportService.getAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Airport created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<Void> registerNewAirport(@RequestBody Airport airport) {
        airportService.addNewAirport(airport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{airportId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Airport deleted"),
            @ApiResponse(responseCode = "404", description = "Airport not found")
    })
    public ResponseEntity<Void> deleteAirport(@PathVariable("airportId") Long airportId) {
        if (airportService.deleteAirport(airportId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "{airportId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Airport updated"),
            @ApiResponse(responseCode = "404", description = "Airport not found")
    })
    public ResponseEntity<Void> updateAirport(@PathVariable("airportId") Long airportId,
                                              @RequestParam(required = false) String newCityName) {
        if (airportService.updateAirport(airportId, newCityName)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
