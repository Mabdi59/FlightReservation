package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightSearchService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(SearchCriteria criteria) {
        // Implement search logic based on criteria
        // This can involve complex database queries
        return flightRepository.findFlights(criteria);
    }
}
