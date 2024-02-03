package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.dao.ReservationDao;
import com.mohamedabdi.skytracker.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JdbcReservationDao implements ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (user_id, flight_reference, search_parameters, reservation_date, status) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, reservation.getUserId(), reservation.getFlightReference(), reservation.getSearchParameters(), reservation.getReservationDate(), reservation.getStatus());
    }

    @Override
    public List<Reservation> getAllReservations() {
        String sql = "SELECT * FROM reservations";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Reservation(rs.getInt("reservation_id"), rs.getInt("user_id"), rs.getString("flight_reference"), rs.getString("search_parameters"), rs.getTimestamp("reservation_date").toLocalDateTime(), rs.getString("status")));
    }

    @Override
    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Reservation(rs.getInt("reservation_id"), rs.getInt("user_id"), rs.getString("flight_reference"), rs.getString("search_parameters"), rs.getTimestamp("reservation_date").toLocalDateTime(), rs.getString("status")));
    }

    @Override
    public int updateReservation(int id, Reservation reservation) {
        String sql = "UPDATE reservations SET user_id = ?, flight_reference = ?, search_parameters = ?, reservation_date = ?, status = ? WHERE reservation_id = ?";
        return jdbcTemplate.update(sql, reservation.getUserId(), reservation.getFlightReference(), reservation.getSearchParameters(), reservation.getReservationDate(), reservation.getStatus(), id);
    }

    @Override
    public int deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
