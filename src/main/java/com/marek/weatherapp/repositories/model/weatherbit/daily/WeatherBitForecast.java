package com.marek.weatherapp.repositories.model.weatherbit.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherBitForecast {
    @JsonProperty("data")
    private List<WeatherBitDaily> dailyForecast;

    public List<WeatherBitDaily> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<WeatherBitDaily> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }
}
