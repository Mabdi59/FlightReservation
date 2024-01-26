package com.mohamedabdi.skytracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightTrackingService {

    private final RestTemplate restTemplate;

    public FlightTrackingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRealTimeFlightData() {
        String url = "https://opensky-network.org/api/states/all";
        return restTemplate.getForObject(url, String.class);
    }
}
