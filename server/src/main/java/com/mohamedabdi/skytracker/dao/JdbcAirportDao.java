package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcAirportDao implements AirportDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAirportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAirport(Airport airport) {
        String sql = "INSERT INTO airports (airport_code, name, city, country) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, airport.getAirportCode(), airport.getName(), airport.getCity(), airport.getCountry());
    }

    @Override
    public Airport getAirportByCode(String airportCode) {
        String sql = "SELECT * FROM airports WHERE airport_code = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{airportCode}, new AirportRowMapper());
    }

    @Override
    public List<Airport> getAllAirports() {
        String sql = "SELECT * FROM airports";
        return jdbcTemplate.query(sql, new AirportRowMapper());
    }

    @Override
    public void updateAirport(Airport airport) {
        String sql = "UPDATE airports SET name = ?, city = ?, country = ? WHERE airport_code = ?";
        jdbcTemplate.update(sql, airport.getName(), airport.getCity(), airport.getCountry(), airport.getAirportCode());
    }

    @Override
    public void deleteAirport(String airportCode) {
        String sql = "DELETE FROM airports WHERE airport_code = ?";
        jdbcTemplate.update(sql, airportCode);
    }

    private static final class AirportRowMapper implements RowMapper<Airport> {
        @Override
        public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
            Airport airport = new Airport();
            airport.setAirportCode(rs.getString("airport_code"));
            airport.setName(rs.getString("name"));
            airport.setCity(rs.getString("city"));
            airport.setCountry(rs.getString("country"));
            return airport;
        }
    }
}
