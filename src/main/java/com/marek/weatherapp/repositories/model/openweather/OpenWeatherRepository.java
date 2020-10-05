package com.marek.weatherapp.repositories.model.openweather;

import com.marek.weatherapp.entities.ForecastEntity;
import com.marek.weatherapp.entities.WeatherForecastEntity;
import com.marek.weatherapp.forecastcache.ForecastDao;
import com.marek.weatherapp.repositories.model.WeatherSource;
import com.marek.weatherapp.repositories.model.openweather.daily.ForecastDaily;
import com.marek.weatherapp.repositories.model.openweather.weather.Coordinates;
import com.marek.weatherapp.repositories.model.openweather.daily.OpenWeatherForecast;
import com.marek.weatherapp.proccesing.JsonProcessing;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class OpenWeatherRepository {
    private final static String URI_PATTERN_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private final static String URI_PATTERN_DAILY = "https://api.openweathermap.org/data/2.5/" +
            "onecall?lat=%f&lon=%f&exclude=minutely,hourly&units=metric&appid=%s";
    private final static LocalDate TOMORROW = LocalDate.now().plusDays(1);

    private final String apiKey;
    private final JsonProcessing jsonP = new JsonProcessing();
    private final ForecastDao forecastDao;

    public OpenWeatherRepository(String apiKey) {
        this.apiKey = apiKey;
        this.forecastDao = new ForecastDao();
    }

    public static String readKey() {
        try {
            return Files.readAllLines(Paths.get("key.txt")).stream().findFirst().orElse("");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    public WeatherForecastEntity getForecast(double latitude, double longitude) {
        return getForecast(latitude, longitude, TOMORROW);
    }

    public WeatherForecastEntity getForecast(double latitude, double longitude, LocalDate date) {
        ForecastEntity previousForecast = findCachedForecast(latitude,longitude,date);
        if(previousForecast != null) {
            System.out.println("Returning cached result!");
            return previousForecast.getWeatherForecast();
        }
        String uri = String.format(URI_PATTERN_DAILY, latitude, longitude, apiKey);
        WeatherForecastEntity weatherForecastEntity = getForecastForDay(uri, date);

        saveCachedForecast(weatherForecastEntity, latitude, longitude, date);
        return weatherForecastEntity;
    }

    public WeatherForecastEntity getForecast(String city) {
        return getForecast(city, TOMORROW);
    }

    public WeatherForecastEntity getForecast(String city, LocalDate date) {
        ForecastEntity previousForecast = findCachedForecast(city,date);
        if(previousForecast != null) {
            System.out.println("Returning cached result!");
            return previousForecast.getWeatherForecast();
        }
        Coordinates coordinates = getCoordinates(city);
        WeatherForecastEntity weatherForecastEntity = getForecast(coordinates.getLatitude(), coordinates.getLongitude(), date);

        saveCachedForecast(weatherForecastEntity, city, date);
        return weatherForecastEntity;
    }


    private Coordinates getCoordinates(String city) {
        String uri = String.format(URI_PATTERN_WEATHER, city, apiKey);
        try {
            return jsonP.getCoordinatesFromOpenWeather(Request.Get(uri).execute().returnContent().asString()).getCoord();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private WeatherForecastEntity getForecastForDay(String uri, LocalDate date) {
        OpenWeatherForecast openWeatherForecast;
        try {
            openWeatherForecast = new JsonProcessing().getForecastFromOpenWeather(Request.Get(uri).execute().returnContent().asString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        List<ForecastDaily> dailies = openWeatherForecast.getForecastDaily();
        return WeatherForecastMapper.fromOpenWeatherForecast(
                dailies.stream()
                .filter(d -> LocalDate.ofEpochDay(d.getDt()/(3600*24)).equals(date))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie ma prognozy na podany dzień")));
    }

    private ForecastEntity findCachedForecast(double latitude, double longitude, LocalDate date){
        return forecastDao.findWeatherForecastForLocalization(WeatherSource.OPEN_WEATHER, String.format("%f;%f", latitude, longitude), date);
    }

    private ForecastEntity findCachedForecast(String city, LocalDate date){
        return forecastDao.findWeatherForecastForLocalization(WeatherSource.OPEN_WEATHER, city, date);
    }

    private void saveCachedForecast(WeatherForecastEntity weatherForecastEntity, double latitude, double longitude, LocalDate date) {
        ForecastEntity forecastEntity = new ForecastEntity(weatherForecastEntity, WeatherSource.OPEN_WEATHER, String.format("%f;%f", latitude, longitude), date);
        forecastDao.addForecast(forecastEntity);
    }

    private void saveCachedForecast(WeatherForecastEntity weatherForecastEntity, String city, LocalDate date) {
        ForecastEntity forecastEntity = new ForecastEntity(weatherForecastEntity, WeatherSource.OPEN_WEATHER, city, date);
        forecastDao.addForecast(forecastEntity);
    }

}
