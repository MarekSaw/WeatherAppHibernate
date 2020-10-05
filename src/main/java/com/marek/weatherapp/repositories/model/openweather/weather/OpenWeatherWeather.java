package com.marek.weatherapp.repositories.model.openweather.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherWeather {
    private Coordinates coordinates;

    public void setCoord(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoord() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "coord=" + coordinates +
                '}';
    }
}
