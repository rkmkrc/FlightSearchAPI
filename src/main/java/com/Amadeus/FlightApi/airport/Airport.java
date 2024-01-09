package com.Amadeus.FlightApi.airport;

import jakarta.persistence.*;

@Entity
@Table
public class Airport {
    @Id
    @SequenceGenerator(
            name = "airport_sequence",
            sequenceName = "airport_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "airport_sequence"
    )
    private Long id;
    @Column(unique = true) // Ensure uniqueness of cityName
    private String cityName;
    public Airport() {
    }
    public Airport(String cityName) {
        this.cityName = cityName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
