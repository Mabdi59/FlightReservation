package com.mohamedabdi.skytracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mohamedabdi.skytracker.model.FlightInfo;

@Service
public class FlightInfoServiceImpl implements FlightInfoService {

    private final RestTemplate restTemplate;

    @Value("${flightaware.apikey}")
    private String apiKey;

    public FlightInfoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FlightInfo getFlightInfoByNumber(String flightNumber) {
        String url = "https://flightxml.flightaware.com/json/FlightXML2/FlightInfoEx?ident=" + flightNumber;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<FlightInfo> response = restTemplate.getForEntity(url, FlightInfo.class);
        return response.getBody();
    }

    @Override
    public FlightInfo getFlightInfo(String flightNumber) {
        return null;
    }
}
