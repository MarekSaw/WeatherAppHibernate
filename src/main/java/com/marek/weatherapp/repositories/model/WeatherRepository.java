package com.marek.weatherapp.repositories.model;

import com.marek.weatherapp.entities.ForecastEntity;
import com.marek.weatherapp.entities.WeatherForecastEntity;
import com.marek.weatherapp.forecastcache.ForecastDao;

import java.time.LocalDate;

public abstract class WeatherRepository {
    private final static ForecastDao forecastDao = new ForecastDao();
    protected final String apiKey;


    public WeatherRepository(String apiKey) {
        this.apiKey = apiKey;
    }

    protected ForecastEntity findCachedForecast(double latitude, double longitude, LocalDate date, WeatherSource source) {
        return forecastDao.findWeatherForecastForLocalization(source, String.format("%f;%f", latitude, longitude), date);
    }

    protected ForecastEntity findCachedForecast(String city, LocalDate date, WeatherSource source) {
        return forecastDao.findWeatherForecastForLocalization(source, city, date);
    }

    protected void saveCachedForecast(WeatherForecastEntity weatherForecastEntity, double latitude, double longitude, LocalDate date, WeatherSource source) {
        ForecastEntity forecastEntity = new ForecastEntity(weatherForecastEntity, source, String.format("%f;%f", latitude, longitude), date);
        forecastDao.addForecast(forecastEntity);
    }

    protected void saveCachedForecast(WeatherForecastEntity weatherForecastEntity, String city, LocalDate date, WeatherSource source) {
        ForecastEntity forecastEntity = new ForecastEntity(weatherForecastEntity, source, city, date);
        forecastDao.addForecast(forecastEntity);
    }
}
