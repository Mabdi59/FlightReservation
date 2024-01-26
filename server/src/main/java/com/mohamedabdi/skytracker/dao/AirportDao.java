package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.Airport;
import java.util.List;

public interface AirportDao {
    void addAirport(Airport airport);
    Airport getAirportByCode(String airportCode);
    List<Airport> getAllAirports();
    void updateAirport(Airport airport);
    void deleteAirport(String airportCode);
}
