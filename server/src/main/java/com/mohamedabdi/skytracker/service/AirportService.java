package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.dao.AirportDao;
import com.mohamedabdi.skytracker.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportDao airportDao;

    @Autowired
    public AirportService(AirportDao airportDao) {
        this.airportDao = airportDao;
    }

    public void addAirport(Airport airport) {
        airportDao.addAirport(airport);
    }

    public Airport getAirportByCode(String airportCode) {
        return airportDao.getAirportByCode(airportCode);
    }

    public List<Airport> getAllAirports() {
        return airportDao.getAllAirports();
    }

    public void updateAirport(Airport airport) {
        airportDao.updateAirport(airport);
    }

    public void deleteAirport(String airportCode) {
        airportDao.deleteAirport(airportCode);
    }
}
