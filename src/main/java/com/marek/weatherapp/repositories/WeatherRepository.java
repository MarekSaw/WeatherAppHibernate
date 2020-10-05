package com.marek.weatherapp.repositories;

import com.marek.weatherapp.entities.WeatherForecastEntity;
import com.marek.weatherapp.repositories.model.WeatherSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface WeatherRepository {
    static String readKey(WeatherSource source) {
        List<String> keys;
        try {
            keys = new ArrayList<>(Files.readAllLines(Paths.get("key.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        switch (source) {
            case OPEN_WEATHER:
                return keys.get(0);
            case WEATHER_BIT:
                return keys.get(1);
        }
        return "";
    }


    WeatherForecastEntity getForecast(String city);
    WeatherForecastEntity getForecast(String city, LocalDate date);

    WeatherForecastEntity getForecast(double lat, double lon);
    WeatherForecastEntity getForecast(double lat, double lon, LocalDate date);
}
