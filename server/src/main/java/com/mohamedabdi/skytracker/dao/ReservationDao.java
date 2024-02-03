package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.Reservation;
import java.util.List;

public interface ReservationDao {
    int addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(int id);
    int updateReservation(int id, Reservation reservation);
    int deleteReservation(int id);
}
