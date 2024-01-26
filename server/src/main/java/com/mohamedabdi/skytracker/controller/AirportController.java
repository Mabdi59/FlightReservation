package com.mohamedabdi.skytracker.controller;

import com.mohamedabdi.skytracker.model.Airport;
import com.mohamedabdi.skytracker.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<?> addAirport(@RequestBody Airport airport) {
        airportService.addAirport(airport);
        return ResponseEntity.ok("Airport added successfully");
    }

    @GetMapping("/{airportCode}")
    public ResponseEntity<Airport> getAirportByCode(@PathVariable String airportCode) {
        Airport airport = airportService.getAirportByCode(airportCode);
        return ResponseEntity.ok(airport);
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @PutMapping("/{airportCode}")
    public ResponseEntity<?> updateAirport(@PathVariable String airportCode, @RequestBody Airport airport) {
        airport.setAirportCode(airportCode);
        airportService.updateAirport(airport);
        return ResponseEntity.ok("Airport updated successfully");
    }

    @DeleteMapping("/{airportCode}")
    public ResponseEntity<?> deleteAirport(@PathVariable String airportCode) {
        airportService.deleteAirport(airportCode);
        return ResponseEntity.ok("Airport deleted successfully");
    }
}
