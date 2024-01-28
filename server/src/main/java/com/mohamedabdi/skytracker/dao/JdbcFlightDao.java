package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.mohamedabdi.skytracker.model.SearchCriteria;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcFlightDao implements FlightDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcFlightDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addFlight(Flight flight) {
        String sql = "INSERT INTO flights (flight_number, airline, departure_airport_code, arrival_airport_code, scheduled_departure_time, scheduled_arrival_time, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, flight.getFlightNumber(), flight.getAirline(), flight.getDepartureAirportCode(), flight.getArrivalAirportCode(), flight.getScheduledDepartureTime(), flight.getScheduledArrivalTime(), flight.getStatus());
    }

    @Override
    public Flight getFlightById(int flightId) {
        String sql = "SELECT * FROM flights WHERE flight_id = ?";
        List<Flight> flights = jdbcTemplate.query(sql, new Object[]{flightId}, new FlightRowMapper());
        return flights.isEmpty() ? null : flights.get(0);
    }


    @Override
    public List<Flight> getAllFlights() {
        String sql = "SELECT * FROM flights";
        return jdbcTemplate.query(sql, new FlightRowMapper());
    }

    // Other methods...

    private static final class FlightRowMapper implements RowMapper<Flight> {
        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
            Flight flight = new Flight();
            flight.setFlightId(rs.getInt("flight_id"));
            flight.setFlightNumber(rs.getString("flight_number"));
            flight.setAirline(rs.getString("airline"));
            // map other fields...
            return flight;
        }
    }
    @Override
    public void updateFlight(Flight flight) {
        String sql = "UPDATE flights SET flight_number = ?, airline = ?, departure_airport_code = ?, arrival_airport_code = ?, scheduled_departure_time = ?, scheduled_arrival_time = ?, status = ? WHERE flight_id = ?";
        jdbcTemplate.update(sql, flight.getFlightNumber(), flight.getAirline(), flight.getDepartureAirportCode(), flight.getArrivalAirportCode(), flight.getScheduledDepartureTime(), flight.getScheduledArrivalTime(), flight.getStatus(), flight.getFlightId());
    }

        @Override
    public void deleteFlight(int flightId) {
        String sql = "DELETE FROM flights WHERE flight_id = ?";
        jdbcTemplate.update(sql, flightId);
    }
    @Override
    public List<Flight> findFlights(SearchCriteria criteria) {
        String sql = "SELECT * FROM flights WHERE ";
        if (criteria.getDepartureCity() != null) {
            sql += "departure_city = '" + criteria.getDepartureCity() + "' AND ";
        }
        sql = sql.substring(0, sql.length() - 5);
        return jdbcTemplate.query(sql, new FlightRowMapper());
    }
}
