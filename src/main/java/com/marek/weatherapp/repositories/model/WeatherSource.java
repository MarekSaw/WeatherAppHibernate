package com.marek.weatherapp.repositories.model;

import com.marek.weatherapp.repositories.WeatherRepository;
import com.marek.weatherapp.repositories.model.openweather.OpenWeatherRepository;
import com.marek.weatherapp.repositories.model.weatherbit.WeatherBitRepository;

public enum WeatherSource {
    OPEN_WEATHER(new OpenWeatherRepository(OpenWeatherRepository.readKey())),
    WEATHER_BIT(new WeatherBitRepository(WeatherBitRepository.readKey()));

    WeatherRepository weatherRepository;

    WeatherSource(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public WeatherRepository getWeatherRepository() {
        return weatherRepository;
    }
}
