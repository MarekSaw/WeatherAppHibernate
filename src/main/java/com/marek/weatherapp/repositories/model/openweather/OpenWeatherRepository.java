package com.marek.weatherapp.repositories.model.openweather;

import com.marek.weatherapp.forecastcache.entities.ForecastEntity;
import com.marek.weatherapp.forecastcache.entities.WeatherForecastEntity;
import com.marek.weatherapp.repositories.WeatherRepository;
import com.marek.weatherapp.forecastcache.CachedForecastRepository;
import com.marek.weatherapp.repositories.model.WeatherForecastMapper;
import com.marek.weatherapp.repositories.model.WeatherSource;
import com.marek.weatherapp.repositories.model.openweather.daily.ForecastDaily;
import com.marek.weatherapp.repositories.model.openweather.coordinates.Coordinates;
import com.marek.weatherapp.repositories.model.openweather.daily.OpenWeatherForecast;
import com.marek.weatherapp.proccesing.JsonProcessing;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OpenWeatherRepository implements WeatherRepository {
    private final static String URI_PATTERN_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private final static String URI_PATTERN_DAILY = "https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&exclude=minutely,hourly&units=metric&appid=%s";
    private final static WeatherSource SOURCE = WeatherSource.OPEN_WEATHER;
    private final static LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private final static JsonProcessing jsonP = new JsonProcessing();

    private String apiKey;

    public OpenWeatherRepository(String apiKey) {
        this.apiKey = apiKey;
    }

    public static String readKey() {
        List<String> keys;
        try {
            keys = new ArrayList<>(Files.readAllLines(Paths.get("key.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return keys.get(0);
    }

    @Override
    public WeatherForecastEntity getForecast(double latitude, double longitude) {
        return getForecast(latitude, longitude, TOMORROW);
    }

    @Override
    public WeatherForecastEntity getForecast(double latitude, double longitude, LocalDate date) {
        ForecastEntity previousForecast = CachedForecastRepository.findCachedForecast(latitude, longitude, date, SOURCE);
        if (previousForecast != null) {
            System.out.println("Returning cached result!");
            return previousForecast.getWeatherForecast();
        }
        String uri = String.format(URI_PATTERN_DAILY, latitude, longitude, apiKey);
        WeatherForecastEntity weatherForecastEntity = getForecastForDay(uri, date);

        CachedForecastRepository.saveCachedForecast(weatherForecastEntity, latitude, longitude, date, SOURCE);
        return weatherForecastEntity;
    }

    @Override
    public WeatherForecastEntity getForecast(String city) {
        return getForecast(city, TOMORROW);
    }

    @Override
    public WeatherForecastEntity getForecast(String city, LocalDate date) {
        ForecastEntity previousForecast = CachedForecastRepository.findCachedForecast(city, date, SOURCE);
        if (previousForecast != null) {
            System.out.println("Returning cached result!");
            return previousForecast.getWeatherForecast();
        }
        Coordinates coordinates = getCoordinates(city);
        WeatherForecastEntity weatherForecastEntity = getForecast(coordinates.getLatitude(), coordinates.getLongitude(), date);

        CachedForecastRepository.saveCachedForecast(weatherForecastEntity, city, date, SOURCE);
        return weatherForecastEntity;
    }


    private Coordinates getCoordinates(String city) {
        String uri = String.format(URI_PATTERN_WEATHER, city, apiKey);
        try {
            return jsonP.getCoordinatesFromOpenWeather(Request.Get(uri).execute().returnContent().asString()).getCoordinates();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private WeatherForecastEntity getForecastForDay(String uri, LocalDate date) {
        OpenWeatherForecast openWeatherForecast;
        try {
            openWeatherForecast = jsonP.getForecastFromOpenWeather(Request.Get(uri).execute().returnContent().asString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        List<ForecastDaily> dailies = openWeatherForecast.getForecastDaily();
        return WeatherForecastMapper.fromOpenWeatherForecast(
                dailies.stream()
                        .filter(d -> LocalDate.ofEpochDay(d.getDt() / (3600 * 24)).equals(date))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("There is no forecast for given date")));
    }

}
