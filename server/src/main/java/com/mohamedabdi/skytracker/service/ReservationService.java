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

    public int addReservation(Reservation reservation) {
        return reservationDao.addReservation(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationDao.getAllReservations();
    }

    public Reservation getReservationById(int id) {
        return reservationDao.getReservationById(id);
    }

    public int updateReservation(int id, Reservation reservation) {
        return reservationDao.updateReservation(id, reservation);
    }

    public int deleteReservation(int id) {
        return reservationDao.deleteReservation(id);
    }
}
