package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.model.AirportInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AirportInfoService {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;

    public AirportInfoService(@Value("${api.airports.base-url}") String baseUrl,
                              @Value("${skyscanner.api.key}") String apiKey,
                              RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    public AirportInfo getAirportInfo(String airportCode) {
        String url = buildAirportInfoUrlWithBuilder(airportCode);
        return restTemplate.getForObject(url, AirportInfo.class);
    }

    private String buildAirportInfoUrlWithBuilder(String airportCode) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("airportCode", airportCode)
                .queryParam("apiKey", apiKey)
                .toUriString();
    }
}
