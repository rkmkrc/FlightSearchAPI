package com.Amadeus.FlightApi.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.id = ?1")
    Optional<Flight> findFlightById(Long id);
    Optional<List<Flight>> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTime(Long departureAirportId, Long arrivalAirportId, String departureDateTime);
}
