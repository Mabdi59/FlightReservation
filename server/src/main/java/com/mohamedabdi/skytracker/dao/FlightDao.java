package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.Flight;
import com.mohamedabdi.skytracker.model.SearchCriteria;
import java.util.List;

public interface FlightDao {
    void addFlight(Flight flight);
    Flight getFlightById(int flightId);
    List<Flight> getAllFlights();

    void updateFlight(Flight existingFlight);

    void deleteFlight(int flightId);

    List<Flight> findFlights(SearchCriteria criteria);
    // Other CRUD methods
}
