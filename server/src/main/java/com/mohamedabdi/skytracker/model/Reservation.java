package com.mohamedabdi.skytracker.model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private int userId;
    private String flightReference;
    private String searchParameters;
    private LocalDateTime reservationDate;
    private String status;

    // Constructors, Getters, and Setters
    public Reservation() {
    }

    public Reservation(int reservationId, int userId, String flightReference, String searchParameters, LocalDateTime reservationDate, String status) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.flightReference = flightReference;
        this.searchParameters = searchParameters;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    // Getters and setters

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFlightReference() {
        return flightReference;
    }

    public void setFlightReference(String flightReference) {
        this.flightReference = flightReference;
    }

    public String getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(String searchParameters) {
        this.searchParameters = searchParameters;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
