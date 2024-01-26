package com.mohamedabdi.skytracker.model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private int userId;
    private int flightId;
    private LocalDateTime reservationDate;
    private String status;

    // Constructors
    public Reservation() {
    }

    public Reservation(int reservationId, int userId, int flightId, LocalDateTime reservationDate, String status) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.flightId = flightId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    // Getters and Setters
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

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
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
