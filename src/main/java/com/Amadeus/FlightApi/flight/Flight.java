package com.Amadeus.FlightApi.flight;

import jakarta.persistence.*;

@Entity
@Table
public class Flight {
    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_sequence"
    )
    private Long id;
    @Column(name = "departure_airport_id")
    private Long departureAirportId;

    @Column(name = "arrival_airport_id")
    private Long arrivalAirportId;
    private String departureDateTime;
    private String returnDateTime;
    private Double price;

    public Flight() {
    }

    public Flight(Long departureAirportId, Long arrivalAirportId, String departureDateTime, String returnDateTime, Double price, Integer numberOfDaysToFlight) {
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;
    }

    public Flight(Long id, Long departureAirportId, Long arrivalAirportId, String departureDateTime, String returnDateTime, Double price) {
        this.id = id;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(String returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departureAirport=" + departureAirportId +
                ", arrivalAirport=" + arrivalAirportId +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + returnDateTime +
                ", price=" + price +
                '}';
    }
//    public Flight(Long id,
//                  Airport departureAirport,
//                  String arrivalAirport,
//                  LocalDate departureDateTime,
//                  LocalDate arrivalDateTime,
//                  Double price) {
//        this.id = id;
//        this.departureAirport = departureAirport;
//        this.arrivalAirport = arrivalAirport;
//        this.departureDateTime = departureDateTime;
//        this.arrivalDateTime = arrivalDateTime;
//        this.price = price;
//    }
//
//    public Flight(Airport departureAirport,
//                  String arrivalAirport,
//                  LocalDate departureDateTime,
//                  LocalDate arrivalDateTime,
//                  Double price) {
//        this.departureAirport = departureAirport;
//        this.arrivalAirport = arrivalAirport;
//        this.departureDateTime = departureDateTime;
//        this.arrivalDateTime = arrivalDateTime;
//        this.price = price;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Airport getDepartureAirport() {
//        return departureAirport;
//    }
//
//    public void setDepartureAirport(Airport departureAirport) {
//        this.departureAirport = departureAirport;
//    }
//
//    public String getArrivalAirport() {
//        return arrivalAirport;
//    }
//
//    public void setArrivalAirport(String arrivalAirport) {
//        this.arrivalAirport = arrivalAirport;
//    }
//
//    public LocalDate getDepartureDateTime() {
//        return departureDateTime;
//    }
//
//    public void setDepartureDateTime(LocalDate departureDateTime) {
//        this.departureDateTime = departureDateTime;
//    }
//
//    public LocalDate getArrivalDateTime() {
//        return arrivalDateTime;
//    }
//
//    public void setArrivalDateTime(LocalDate arrivalDateTime) {
//        this.arrivalDateTime = arrivalDateTime;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    @Override
//    public String toString() {
//        return "Flight{" +
//                "id=" + id +
//                ", departureAirport='" + departureAirport + '\'' +
//                ", arrivalAirport='" + arrivalAirport + '\'' +
//                ", departureDateTime=" + departureDateTime +
//                ", arrivalDateTime=" + arrivalDateTime +
//                ", price=" + price +
//                '}';
//    }
}
