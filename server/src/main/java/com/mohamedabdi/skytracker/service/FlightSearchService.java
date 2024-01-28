package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.dao.FlightDao;
import com.mohamedabdi.skytracker.model.Flight;
import com.mohamedabdi.skytracker.model.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightSearchService {

    private final FlightDao flightDao;

    @Autowired
    public FlightSearchService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public List<Flight> searchFlights(SearchCriteria criteria) {
        return flightDao.findFlights(criteria);
    }
}
