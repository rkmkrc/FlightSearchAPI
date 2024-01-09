package com.Amadeus.FlightApi.airport;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    private final AirportRepository airportRepository;
    @Autowired
    public AirportService(AirportRepository airportRepository) {this.airportRepository = airportRepository;}
    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }
    public Airport getAirportWithId(Long airportId) {
        Optional<Airport> optionalAirport = airportRepository.findAirportById(airportId);
        if (optionalAirport.isPresent()) {
            return optionalAirport.get();
        } else {
            throw new IllegalStateException("Airport with id = " + airportId + " does not exist.");
        }
    }
    public void addNewAirport(Airport airport) {
        Optional<Airport> optionalAirport = airportRepository.findAirportById(airport.getId());
        if (optionalAirport.isPresent()) {
            throw new IllegalStateException("Airport id = " + airport.getId() +" already exists.");
        }
        airportRepository.save(airport);
    }
    public boolean deleteAirport(Long airportId) {
        boolean exists = airportRepository.existsById(airportId);
        if (!exists) {
            throw new IllegalStateException("Airport id = " + airportId +" does not exists.");
        }
        airportRepository.deleteById(airportId);
        return true;
    }
    @Transactional
    public boolean updateAirport(Long airportId, String newCityName) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() ->
                new IllegalStateException("Airport id = %d does not exists.".formatted(airportId)));
        if (newCityName != null && !newCityName.isEmpty()) {
            airport.setCityName(newCityName);
            return true;
        }
        return false;
    }
}
