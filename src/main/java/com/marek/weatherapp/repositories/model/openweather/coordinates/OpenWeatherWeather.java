package com.marek.weatherapp.repositories.model.openweather.coordinates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherWeather {
    @JsonProperty("coord")
    private Coordinates coordinates;

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "coordinates=" + coordinates +
                '}';
    }
}
