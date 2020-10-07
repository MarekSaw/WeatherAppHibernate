package com.marek.weatherapp.repositories;

import com.marek.weatherapp.forecastcache.entities.WeatherForecastEntity;

import java.time.LocalDate;

public interface WeatherRepository {

    WeatherForecastEntity getForecast(String city);
    WeatherForecastEntity getForecast(String city, LocalDate date);

    WeatherForecastEntity getForecast(double lat, double lon);
    WeatherForecastEntity getForecast(double lat, double lon, LocalDate date);
}
