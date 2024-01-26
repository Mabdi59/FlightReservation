package com.mohamedabdi.skytracker.controller;

import com.mohamedabdi.skytracker.service.FlightTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flight-tracking")
public class FlightTrackingController {

    private final FlightTrackingService flightTrackingService;

    @Autowired
    public FlightTrackingController(FlightTrackingService flightTrackingService) {
        this.flightTrackingService = flightTrackingService;
    }

    @GetMapping("/realtime")
    public ResponseEntity<String> getRealtimeFlights() {
        String flightData = flightTrackingService.getRealTimeFlightData();
        return ResponseEntity.ok(flightData);
    }
}
