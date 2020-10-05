package com.marek.weatherapp.repositories.model.openweather;

import com.marek.weatherapp.repositories.model.openweather.daily.ForecastDaily;
import com.marek.weatherapp.entities.WeatherForecastEntity;

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
}
