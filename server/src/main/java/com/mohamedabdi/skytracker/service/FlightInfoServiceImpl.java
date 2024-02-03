package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.model.FlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FlightInfoServiceImpl implements FlightInfoService {

    private final RestTemplate restTemplate;

    @Autowired
    public FlightInfoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public FlightInfo getFlightInfo(String flightNumber) {
        String apiUrl = "https://opensky-network.org/api/states/all";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("icao24", flightNumber);

        FlightInfo response = restTemplate.getForObject(builder.toUriString(), FlightInfo.class);
        return response;
    }
}
