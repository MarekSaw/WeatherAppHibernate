package com.marek.weatherapp.repositories.model.openweather.daily;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherForecast {
    private double lat;
    private double lon;
    private String timezone;
    @JsonProperty("timezone_offset")
    private int timezoneOffset;
    @JsonProperty("current")
    private ForecastCurrent forecastCurrent;
    @JsonProperty("daily")
    private List<ForecastDaily> forecastDaily;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getTimezoneOffset() {
        return timezoneOffset;
    }

    public ForecastCurrent getForecastCurrent() {
        return forecastCurrent;
    }

    public List<ForecastDaily> getForecastDaily() {
        return forecastDaily;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTimezoneOffset(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public void setForecastCurrent(ForecastCurrent forecastCurrent) {
        this.forecastCurrent = forecastCurrent;
    }

    public void setForecastDaily(List<ForecastDaily> forecastDaily) {
        this.forecastDaily = forecastDaily;
    }

    @Override
    public String toString() {
        return "OpenWeatherForecast{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", timezone='" + timezone + '\'' +
                ", timezoneOffset=" + timezoneOffset +
                ", current=" + forecastCurrent +
                ", daily=" + forecastDaily +
                '}';
    }
}
