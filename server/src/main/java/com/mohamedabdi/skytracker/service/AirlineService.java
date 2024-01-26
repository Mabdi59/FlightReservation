package com.mohamedabdi.skytracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirlineService {

    private final RestTemplate restTemplate;
    private final String apiKey = "5K379uRCzZmjS7MDIJAz4ch9xtJbakdHAcwV4AVPRrdw03vVT/IyTbIw5VMu4vgd";

    public AirlineService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllAirlines() {
        String url = "https://api.aviationstack.com/v1/airlines" + "?access_key=" + apiKey; // Replace with Aviationstack API endpoint
        return restTemplate.getForObject(url, String.class);
    }

    // Additional methods as needed...
}
