package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.model.FlightInfo;

public interface FlightInfoService {
    FlightInfo getFlightInfo(String flightNumber);
    // Other methods as needed, e.g., searchFlights(...)
}
