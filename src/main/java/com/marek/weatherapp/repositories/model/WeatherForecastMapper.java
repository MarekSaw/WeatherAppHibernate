package com.marek.weatherapp.repositories.model;

import com.marek.weatherapp.repositories.model.openweather.daily.ForecastDaily;
import com.marek.weatherapp.forecastcache.entities.WeatherForecastEntity;
import com.marek.weatherapp.repositories.model.weatherbit.daily.WeatherBitDaily;

public class WeatherForecastMapper {
    public static WeatherForecastEntity fromOpenWeatherForecast(ForecastDaily forecastDaily) {
        WeatherForecastEntity result = new WeatherForecastEntity();

        result.setHumidity(forecastDaily.getHumidity());
        result.setPressure(forecastDaily.getPressure());
        result.setTemperature(forecastDaily.getForecastDailyTemp().getDay());
        result.setWindDeg(forecastDaily.getWindDeg());
        result.setWindSpeed(forecastDaily.getWindSpeed());

        return result;
    }

    public static WeatherForecastEntity fromWeatherBitForecast(WeatherBitDaily weatherBitDaily) {
        WeatherForecastEntity result = new WeatherForecastEntity();

        result.setHumidity(weatherBitDaily.getHumidity());
        result.setPressure(weatherBitDaily.getPressure());
        result.setTemperature(weatherBitDaily.getTemperature());
        result.setWindDeg(weatherBitDaily.getWindDeg());
        result.setWindSpeed(weatherBitDaily.getWindSpeed());

        return result;
    }
}
