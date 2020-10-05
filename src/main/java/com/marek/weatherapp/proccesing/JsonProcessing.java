package com.marek.weatherapp.proccesing;

import com.marek.weatherapp.repositories.model.openweather.daily.OpenWeatherForecast;
import com.marek.weatherapp.repositories.model.openweather.coordinates.OpenWeatherWeather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marek.weatherapp.repositories.model.weatherbit.daily.WeatherBitForecast;

public class JsonProcessing {

    public OpenWeatherForecast getForecastFromOpenWeather(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(json, OpenWeatherForecast.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OpenWeatherWeather getCoordinatesFromOpenWeather(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(json, OpenWeatherWeather.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public WeatherBitForecast getForecastFromWeatherBit(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(json, WeatherBitForecast.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
