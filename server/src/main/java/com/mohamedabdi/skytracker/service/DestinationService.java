package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.dao.DestinationDao;
import com.mohamedabdi.skytracker.model.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private final DestinationDao destinationDao;

    @Autowired
    public DestinationService(DestinationDao destinationDao) {
        this.destinationDao = destinationDao;
    }

    public void addDestination(Destination destination) {
        destinationDao.addDestination(destination);
    }

    public Destination getDestinationByCity(String city) {
        return destinationDao.getDestinationByCity(city);
    }

    public List<Destination> getAllDestinations() {
        return destinationDao.getAllDestinations();
    }

    public void updateDestination(Destination destination) {
        destinationDao.updateDestination(destination);
    }

    public void deleteDestination(String city) {
        destinationDao.deleteDestination(city);
    }
}
