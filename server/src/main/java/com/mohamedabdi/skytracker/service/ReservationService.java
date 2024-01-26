package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.dao.ReservationDao;
import com.mohamedabdi.skytracker.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;

    @Autowired
    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public void addReservation(Reservation reservation) {
        reservationDao.addReservation(reservation);
    }

    public Reservation getReservationById(int id) {
        return reservationDao.getReservationById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationDao.getAllReservations();
    }

    public void updateReservation(Reservation reservation) {
        reservationDao.updateReservation(reservation);
    }

    public void deleteReservation(int id) {
        reservationDao.deleteReservation(id);
    }

}
