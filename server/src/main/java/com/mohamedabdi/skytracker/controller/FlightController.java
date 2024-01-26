package com.mohamedabdi.skytracker.controller;

import com.mohamedabdi.skytracker.model.Flight;
import com.mohamedabdi.skytracker.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
        return ResponseEntity.ok(flight);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") int flightId) {
        Flight flight = flightService.getFlightById(flightId);
        return ResponseEntity.ok(flight);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("id") int flightId, @RequestBody Flight flightDetails) {
        Flight updatedFlight = flightService.updateFlight(flightId, flightDetails);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable("id") int flightId) {
        flightService.deleteFlight(flightId);
        return ResponseEntity.ok().build();
    }

}
