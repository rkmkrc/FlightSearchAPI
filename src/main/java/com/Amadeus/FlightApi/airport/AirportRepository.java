package com.Amadeus.FlightApi.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query("SELECT a FROM Airport a WHERE a.id = ?1")
    Optional<Airport> findAirportById(Long id);

    Airport findByCityName(String cityName);
}
