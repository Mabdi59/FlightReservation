package com.mohamedabdi.skytracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenSkyService {

    private final RestTemplate restTemplate;

    public OpenSkyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllFlights() {
        String url = "https://opensky-network.org/api/states/all";
        return restTemplate.getForObject(url, String.class);
    }
}