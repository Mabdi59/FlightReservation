package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.Reservation;
import java.util.List;

public interface ReservationDao {
    void addReservation(Reservation reservation);
    Reservation getReservationById(int id);
    List<Reservation> getAllReservations();
    void updateReservation(Reservation reservation);
    void deleteReservation(int id);
}
