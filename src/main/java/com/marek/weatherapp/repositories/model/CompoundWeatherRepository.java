package com.marek.weatherapp.repositories.model;

import com.marek.weatherapp.forecastcache.entities.WeatherForecastEntity;
import com.marek.weatherapp.repositories.WeatherRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CompoundWeatherRepository implements WeatherRepository {
    private final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private final List<WeatherRepository> repositoryList;

    public CompoundWeatherRepository(List<WeatherRepository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    @Override
    public WeatherForecastEntity getForecast(String city) {
        return getAverageForecast(repositoryList.stream()
                .map(r -> r.getForecast(city, TOMORROW))
                .collect(Collectors.toList()));
    }

    @Override
    public WeatherForecastEntity getForecast(String city, LocalDate date) {
        return getAverageForecast(repositoryList.stream()
                .map(r -> r.getForecast(city, date))
                .collect(Collectors.toList()));
    }

    @Override
    public WeatherForecastEntity getForecast(double lat, double lon) {
        return getAverageForecast(repositoryList.stream()
                .map(r -> r.getForecast(lat, lon, TOMORROW))
                .collect(Collectors.toList()));
    }

    @Override
    public WeatherForecastEntity getForecast(double lat, double lon, LocalDate date) {
        return getAverageForecast(repositoryList.stream()
                .map(r -> r.getForecast(lat, lon, date))
                .collect(Collectors.toList()));
    }

    private static WeatherForecastEntity getAverageForecast(List<WeatherForecastEntity> list) {
        return new WeatherForecastEntity(
                list.stream().map(WeatherForecastEntity::getTemperature).reduce(0.0, Double::sum) / list.size(),
                list.stream().map(WeatherForecastEntity::getPressure).reduce(0.0, Double::sum) / list.size(),
                list.stream().map(WeatherForecastEntity::getHumidity).reduce(0.0, Double::sum) / list.size(),
                list.stream().map(WeatherForecastEntity::getWindSpeed).reduce(0.0, Double::sum) / list.size(),
                list.stream().map(WeatherForecastEntity::getWindDeg).reduce(0.0, Double::sum) / list.size());
    }
}
