package com.marek.weatherapp.forecastcache;

import com.marek.weatherapp.forecastcache.entities.ForecastEntity;
import com.marek.weatherapp.forecastcache.entities.WeatherForecastEntity;
import com.marek.weatherapp.repositories.model.WeatherSource;

import java.time.LocalDate;

public abstract class CachedForecastRepository {
    private final static ForecastDao forecastDao = new ForecastDao();

    public static ForecastEntity findCachedForecast(double latitude, double longitude, LocalDate date, WeatherSource source) {
        return forecastDao.findWeatherForecastForLocalization(source, String.format("%f;%f", latitude, longitude), date);
    }

    public static ForecastEntity findCachedForecast(String city, LocalDate date, WeatherSource source) {
        return forecastDao.findWeatherForecastForLocalization(source, city, date);
    }

    public static void saveCachedForecast(WeatherForecastEntity weatherForecastEntity, double latitude, double longitude, LocalDate date, WeatherSource source) {
        ForecastEntity forecastEntity = new ForecastEntity(weatherForecastEntity, source, String.format("%f;%f", latitude, longitude), date);
        forecastDao.addForecast(forecastEntity);
    }

    public static void saveCachedForecast(WeatherForecastEntity weatherForecastEntity, String city, LocalDate date, WeatherSource source) {
        ForecastEntity forecastEntity = new ForecastEntity(weatherForecastEntity, source, city, date);
        forecastDao.addForecast(forecastEntity);
    }
}
