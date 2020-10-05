package com.marek.weatherapp.repositories.model.openweather.coordinates;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {
    @JsonProperty("lon")
    private double longitude;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("name")
    private String cityName;

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
