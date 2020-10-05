package com.marek.weatherapp.repositories.model.weatherbit;


import com.marek.weatherapp.entities.ForecastEntity;
import com.marek.weatherapp.entities.WeatherForecastEntity;
import com.marek.weatherapp.proccesing.JsonProcessing;
import com.marek.weatherapp.repositories.WeatherRepository;
import com.marek.weatherapp.repositories.model.WeatherSource;
import com.marek.weatherapp.repositories.model.openweather.WeatherForecastMapper;
import com.marek.weatherapp.repositories.model.weatherbit.daily.WeatherBitDaily;
import com.marek.weatherapp.repositories.model.weatherbit.daily.WeatherBitForecast;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class WeatherBitRepository extends com.marek.weatherapp.repositories.model.WeatherRepository implements WeatherRepository {
    private final static String URI_PATTERN_WEATHER_CITY = "https://api.weatherbit.io/v2.0/forecast/daily?key=%s&city=%s&units=M";
    private final static String URI_PATTERN_WEATHER_COORDINATES = "https://api.weatherbit.io/v2.0/forecast/daily?key=%s&units=M&lat=%f&lon=%f";
    private final static WeatherSource SOURCE = WeatherSource.WEATHER_BIT;
    private final static LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private final static JsonProcessing jsonP = new JsonProcessing();

    public WeatherBitRepository(String apiKey) {
        super(apiKey);
    }

    public WeatherForecastEntity getForecast(double latitude, double longitude) {
        return getForecast(latitude, longitude, TOMORROW);
    }

    public WeatherForecastEntity getForecast(double latitude, double longitude, LocalDate date) {
        ForecastEntity previousForecast = findCachedForecast(latitude, longitude, date, SOURCE);
        if (previousForecast != null) {
            System.out.println("Returning cached result!");
            return previousForecast.getWeatherForecast();
        }

        String uri = String.format(URI_PATTERN_WEATHER_COORDINATES, apiKey, latitude, longitude).replace(',','.');
        WeatherForecastEntity weatherForecastEntity = getForecastForDay(uri, date);

        saveCachedForecast(weatherForecastEntity, latitude, longitude, date, SOURCE);
        return weatherForecastEntity;
    }

    public WeatherForecastEntity getForecast(String city) {
        return getForecast(city, TOMORROW);
    }

    public WeatherForecastEntity getForecast(String city, LocalDate date) {
        ForecastEntity previousForecast = findCachedForecast(city, date, SOURCE);
        if (previousForecast != null) {
            System.out.println("Returning cached result!");
            return previousForecast.getWeatherForecast();
        }

        String uri = String.format(URI_PATTERN_WEATHER_CITY, apiKey, city);
        WeatherForecastEntity weatherForecastEntity = getForecastForDay(uri, date);

        saveCachedForecast(weatherForecastEntity, city, date, SOURCE);
        return weatherForecastEntity;

    }

    private WeatherForecastEntity getForecastForDay(String uri, LocalDate date) {
        WeatherBitForecast weatherBitForecast;
        try {
            weatherBitForecast = jsonP.getForecastFromWeatherBit(Request.Get(uri).execute().returnContent().asString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        List<WeatherBitDaily> dailies = weatherBitForecast.getDailyForecast();
        return WeatherForecastMapper.fromWeatherBitForecast(
                dailies.stream()
                        .filter(d -> LocalDate.parse(d.getDate()).equals(date))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("There is no forecast for given date")));
    }

    
}
