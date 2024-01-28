package com.mohamedabdi.skytracker.model;

public class SearchCriteria {
    private String departureCity;
    private String arrivalCity;
    private String date;

    // Constructors, getters, and setters for each field


    public SearchCriteria(String departureCity, String arrivalCity, String date) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.date = date;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    // Implement other getters and setters...
}
