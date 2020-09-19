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
    private final static String URI_PATTERN = "https://api.openweathermap.org/data/2.5/" +
            "onecall?lat=%f&lon=%f&exclude=minutely,hourly&units=metric&appid=%s";

    public OpenWeatherRepository(String apiKey) {
        this.apiKey = apiKey;
    }


    public Daily getForecast(double latitude, double longitude) {
        String uri = String.format(URI_PATTERN, latitude, longitude, apiKey);
        return getForecastForDay(uri, LocalDate.now().plusDays(1));
    }

    public Daily getForecast(double latitude, double longitude, LocalDate date) {
        String uri = String.format(URI_PATTERN, latitude, longitude, apiKey);
        return getForecastForDay(uri, date);
    }

    public Daily getForecast(String city, LocalDate date) {
        Coord coord = getCoordinates(city);
        String uri = String.format(URI_PATTERN, coord.getLatitude(), coord.getLongitude(), apiKey);
        return getForecastForDay(uri, date);
    }

    public Daily getForecast(String city) {
        Coord coord = getCoordinates(city);
        String uri = String.format(URI_PATTERN, coord.getLatitude(), coord.getLongitude(), apiKey);
        return getForecastForDay(uri, LocalDate.now().plusDays(1));
    }

    private Coord getCoordinates(String city) {
        String uriPattern = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
        String uri = String.format(uriPattern, city, apiKey);
        try {
            return jsonP.getClassFromJSONString(Request.Get(uri).execute().returnContent().asString()).getCoord();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private Daily getForecastForDay(String uri, LocalDate date) {
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

}
