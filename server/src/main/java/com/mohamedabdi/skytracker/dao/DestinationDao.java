package com.mohamedabdi.skytracker.dao;
import com.mohamedabdi.skytracker.model.Destination;
import java.util.List;
public interface DestinationDao {
    void addDestination(Destination destination);
    Destination getDestinationByCity(String city);
    List<Destination> getAllDestinations();
    void updateDestination(Destination destination);
    void deleteDestination(String city);
}
