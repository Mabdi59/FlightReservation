package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.dao.FlightDao;
import com.mohamedabdi.skytracker.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightDao flightDao;

    @Autowired
    public FlightService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public void addFlight(Flight flight) {
        flightDao.addFlight(flight);
    }

    public Flight getFlightById(int flightId) {
        return flightDao.getFlightById(flightId);
    }

    public List<Flight> getAllFlights() {
        return flightDao.getAllFlights();
    }

    public Flight updateFlight(int flightId, Flight flightDetails) {
        // Fetch the existing flight from the database
        Flight existingFlight = flightDao.getFlightById(flightId);
        // Update existing flight with new details
        if (existingFlight != null) {
            existingFlight.setFlightNumber(flightDetails.getFlightNumber());
            existingFlight.setAirline(flightDetails.getAirline());
            // Set other fields...
            flightDao.updateFlight(existingFlight);
        }
        return existingFlight;
    }

    public void deleteFlight(int flightId) {
        flightDao.deleteFlight(flightId);
    }
}
