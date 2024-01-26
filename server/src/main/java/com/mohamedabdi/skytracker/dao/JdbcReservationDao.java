package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcReservationDao implements ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (user_id, flight_id, reservation_date, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, reservation.getUserId(), reservation.getFlightId(), reservation.getReservationDate(), reservation.getStatus());
    }

    @Override
    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ReservationRowMapper());
    }

    @Override
    public List<Reservation> getAllReservations() {
        String sql = "SELECT * FROM reservations";
        return jdbcTemplate.query(sql, new ReservationRowMapper());
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET user_id = ?, flight_id = ?, reservation_date = ?, status = ? WHERE reservation_id = ?";
        jdbcTemplate.update(sql, reservation.getUserId(), reservation.getFlightId(), reservation.getReservationDate(), reservation.getStatus(), reservation.getReservationId());
    }

    @Override
    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            Reservation reservation = new Reservation();
            reservation.setReservationId(rs.getInt("reservation_id"));
            reservation.setUserId(rs.getInt("user_id"));
            reservation.setFlightId(rs.getInt("flight_id"));
            reservation.setReservationDate(rs.getTimestamp("reservation_date").toLocalDateTime());
            reservation.setStatus(rs.getString("status"));
            return reservation;
        }
    }
}
