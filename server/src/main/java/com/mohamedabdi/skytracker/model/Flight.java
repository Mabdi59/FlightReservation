package com.mohamedabdi.skytracker.model;

import java.time.LocalDateTime;

public class Flight {
    private int flightId;
    private String flightNumber;
    private String airline;
    private String departureAirportCode;
    private String arrivalAirportCode;
    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime scheduledArrivalTime;
    private LocalDateTime realTimeDepartureTime;
    private LocalDateTime realTimeArrivalTime;
    private String status;

    // Constructors, getters, setters

    public Flight(int flightId, String flightNumber, String airline, String departureAirportCode, String arrivalAirportCode, LocalDateTime scheduledDepartureTime, LocalDateTime scheduledArrivalTime, LocalDateTime realTimeDepartureTime, LocalDateTime realTimeArrivalTime, String status) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.realTimeDepartureTime = realTimeDepartureTime;
        this.realTimeArrivalTime = realTimeArrivalTime;
        this.status = status;
    }

    public Flight() {

    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public LocalDateTime getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(LocalDateTime scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public LocalDateTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public void setScheduledArrivalTime(LocalDateTime scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public LocalDateTime getRealTimeDepartureTime() {
        return realTimeDepartureTime;
    }

    public void setRealTimeDepartureTime(LocalDateTime realTimeDepartureTime) {
        this.realTimeDepartureTime = realTimeDepartureTime;
    }

    public LocalDateTime getRealTimeArrivalTime() {
        return realTimeArrivalTime;
    }

    public void setRealTimeArrivalTime(LocalDateTime realTimeArrivalTime) {
        this.realTimeArrivalTime = realTimeArrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
