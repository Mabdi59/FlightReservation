package com.mohamedabdi.skytracker.controller;

import com.mohamedabdi.skytracker.model.AirportInfo;
import com.mohamedabdi.skytracker.service.AirportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airports")
public class AirportInfoController {

    private final AirportInfoService airportInfoService;

    @Autowired
    public AirportInfoController(AirportInfoService airportInfoService) {
        this.airportInfoService = airportInfoService;
    }

    @GetMapping("/{airportCode}")
    public ResponseEntity<?> getAirportInfo(@PathVariable String airportCode) {
        try {
            AirportInfo airportInfo = airportInfoService.getAirportInfo(airportCode);
            if (airportInfo != null) {
                return new ResponseEntity<>(airportInfo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Airport information not found for code: " + airportCode, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception details here with a logger
            return new ResponseEntity<>("An error occurred while fetching airport information.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
