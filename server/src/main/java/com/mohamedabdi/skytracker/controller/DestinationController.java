package com.mohamedabdi.skytracker.controller;

import com.mohamedabdi.skytracker.model.Destination;
import com.mohamedabdi.skytracker.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping
    public ResponseEntity<Void> addDestination(@RequestBody Destination destination) {
        destinationService.addDestination(destination);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{city}")
    public ResponseEntity<Destination> getDestinationByCity(@PathVariable String city) {
        Destination destination = destinationService.getDestinationByCity(city);
        return ResponseEntity.ok(destination);
    }

    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        List<Destination> destinations = destinationService.getAllDestinations();
        return ResponseEntity.ok(destinations);
    }

    @PutMapping("/{city}")
    public ResponseEntity<Void> updateDestination(@PathVariable String city, @RequestBody Destination destination) {
        destination.setCity(city);
        destinationService.updateDestination(destination);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{city}")
    public ResponseEntity<Void> deleteDestination(@PathVariable String city) {
        destinationService.deleteDestination(city);
        return ResponseEntity.ok().build();
    }
}
