package com.marek.weatherapp.repositories.model;

import com.marek.weatherapp.repositories.WeatherRepository;
import com.marek.weatherapp.repositories.model.openweather.OpenWeatherRepository;
import com.marek.weatherapp.repositories.model.weatherbit.WeatherBitRepository;

public enum WeatherSource {
    OPEN_WEATHER,
    WEATHER_BIT
}
