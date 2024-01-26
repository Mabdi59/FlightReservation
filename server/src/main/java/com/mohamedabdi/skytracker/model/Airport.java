package com.mohamedabdi.skytracker.model;

public class Airport {
    private String airportCode;
    private String name;
    private String city;
    private String country;

    // Constructor
    public Airport() {
    }

    public Airport(String airportCode, String name, String city, String country) {
        this.airportCode = airportCode;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    // Getters and setters
    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Airport{" +
                "airportCode='" + airportCode + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
