package com.marek.weatherapp.repositories;

import com.marek.weatherapp.entities.WeatherForecastEntity;

import java.time.LocalDate;

public interface WeatherRepository {

    // todo: dodać metodę do odczytywania kluczy po enumie sprawdzajaca do jakiej platformy pogodowej

    WeatherForecastEntity getForecast(String city, LocalDate date);

    WeatherForecastEntity getForecast(double lat, double lon, LocalDate date);
}
