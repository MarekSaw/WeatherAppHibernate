package Repositories;

import Proccesing.Daily;
import Proccesing.Forecast;
import Proccesing.JSONForecastProcessing;
import Proccesing.coordinates.Coord;
import Proccesing.coordinates.JsonProcessing;
import org.apache.http.client.fluent.Request;
import java.io.IOException;
import java.time.LocalDate;


public class OpenWeatherRepository {
    private final String apiKey;
    private final JsonProcessing jsonP = new JsonProcessing();

    public OpenWeatherRepository(String apiKey) {
        this.apiKey = apiKey;
    }


    public Daily getForecast(double latitude, double longitude) {
        String uriPattern = "https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&exclude=minutely,hourly" +
                "&units=metric&appid=%s";
        String uri = String.format(uriPattern, latitude, longitude, apiKey);
        Forecast forecast;

        try {
            forecast = new JSONForecastProcessing().getClassFromJSONString(Request.Get(uri).execute().returnContent().asString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Daily[] dailies = forecast.getDaily();
        Daily result = new Daily();
        for(Daily d : dailies) {
            if(LocalDate.ofEpochDay(d.getDt()/(3600*24)).equals(LocalDate.now().plusDays(1))){
                result = d;
            }
        }
        return result;
    }

    public Daily getForecast(double latitude, double longitude, LocalDate date) {
        String uriPattern = "https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&exclude=minutely,hourly" +
                "&units=metric&appid=%s";
        String uri = String.format(uriPattern, latitude, longitude, apiKey);
        Forecast forecast;

        try {
            forecast = new JSONForecastProcessing().getClassFromJSONString(Request.Get(uri).execute().returnContent().asString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Daily[] dailies = forecast.getDaily();
        Daily result = new Daily();
        for(Daily d : dailies) {
            if(LocalDate.ofEpochDay(d.getDt()/(3600*24)).equals(date)){
                result = d;
            }
        }
        return result;
    }

    public Daily getForecast(String city, LocalDate date) {
        Coord coord = getCoordinates(city);

        String uriPattern = "https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&exclude=minutely,hourly" +
                "&units=metric&appid=%s";
        String uri = String.format(uriPattern, coord.getLatitude(), coord.getLongitude(), apiKey);
        Forecast forecast;

        try {
            forecast = new JSONForecastProcessing().getClassFromJSONString(Request.Get(uri).execute().returnContent().asString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Daily[] dailies = forecast.getDaily();
        Daily result = new Daily();
        for(Daily d : dailies) {
            if(LocalDate.ofEpochDay(d.getDt()/(3600*24)).equals(date)){
                result = d;
            }
        }
        return result;
    }

    public Daily getForecast(String city) {
        Coord coord = getCoordinates(city);

        String uriPattern = "https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&exclude=minutely,hourly" +
                "&units=metric&appid=%s";
        String uri = String.format(uriPattern, coord.getLatitude(), coord.getLongitude(), apiKey);
        Forecast forecast;

        try {
            forecast = new JSONForecastProcessing().getClassFromJSONString(Request.Get(uri).execute().returnContent().asString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Daily[] dailies = forecast.getDaily();
        Daily result = new Daily();
        for(Daily d : dailies) {
            if(LocalDate.ofEpochDay(d.getDt()/(3600*24)).equals(LocalDate.now().plusDays(1))){
                result = d;
            }
        }
        return result;
    }

    public Coord getCoordinates(String city) {
        String uriPattern = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
        String uri = String.format(uriPattern, city, apiKey);
        try {
            return jsonP.getClassFromJSONString(Request.Get(uri).execute().returnContent().asString()).getCoord();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
