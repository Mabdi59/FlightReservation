package com.mohamedabdi.skytracker.model;

public class Destination {
    private String city;
    private String country;
    private String description;
    private String imageUrl;
    private String virtualTourUrl;

    // Constructors
    public Destination() {
    }

    public Destination(String city, String country, String description, String imageUrl, String virtualTourUrl) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.imageUrl = imageUrl;
        this.virtualTourUrl = virtualTourUrl;
    }

    // Getters and Setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVirtualTourUrl() {
        return virtualTourUrl;
    }

    public void setVirtualTourUrl(String virtualTourUrl) {
        this.virtualTourUrl = virtualTourUrl;
    }
}
