package com.mohamedabdi.skytracker.controller;

import com.mohamedabdi.skytracker.model.FlightInfo;
import com.mohamedabdi.skytracker.service.FlightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight-info")
public class FlightInfoController {

    private final FlightInfoService flightInfoService;

    @Autowired
    public FlightInfoController(FlightInfoService flightInfoService) {
        this.flightInfoService = flightInfoService;
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<FlightInfo> getFlightInfo(@PathVariable String flightNumber) {
        FlightInfo flightInfo = flightInfoService.getFlightInfo(flightNumber);
        if (flightInfo != null) {
            return ResponseEntity.ok(flightInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
