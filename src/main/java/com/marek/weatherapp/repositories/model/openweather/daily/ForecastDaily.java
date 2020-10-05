package com.marek.weatherapp.repositories.model.openweather.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDaily {
    private int dt;
    private int sunrise;
    private int sunset;
    @JsonProperty("temp")
    private ForecastDailyTemp forecastDailyTemp;
    private ForecastDailyFeelsLike feels_like;
    private int pressure;
    private int humidity;
    @JsonProperty("dew_point")
    private double dewPoint;
    @JsonProperty("wind_speed")
    private double windSpeed;
    @JsonProperty("wind_deg")
    private int windDeg;
    private List<ForecastWeather> forecastWeather;
    private int clouds;
    private int pop;
    private double rain;
    private double uvi;

    public int getDt() {
        return dt;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public ForecastDailyTemp getForecastDailyTemp() {
        return forecastDailyTemp;
    }

    public ForecastDailyFeelsLike getFeels_like() {
        return feels_like;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public List<ForecastWeather> getForecastWeather() {
        return forecastWeather;
    }

    public int getClouds() {
        return clouds;
    }

    public int getPop() {
        return pop;
    }

    public double getRain() {
        return rain;
    }

    public double getUvi() {
        return uvi;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public void setForecastDailyTemp(ForecastDailyTemp forecastDailyTemp) {
        this.forecastDailyTemp = forecastDailyTemp;
    }

    public void setFeels_like(ForecastDailyFeelsLike feels_like) {
        this.feels_like = feels_like;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public void setForecastWeather(List<ForecastWeather> forecastWeather) {
        this.forecastWeather = forecastWeather;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public void setUvi(double uvi) {
        this.uvi = uvi;
    }

    @Override
    public String toString() {
        return "ForecastDaily{" +
                "dt=" + dt +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", forecastDailyTemp=" + forecastDailyTemp +
                ", feels_like=" + feels_like +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", dewPoint=" + dewPoint +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDeg +
                ", forecastWeather=" + forecastWeather +
                ", clouds=" + clouds +
                ", pop=" + pop +
                ", rain=" + rain +
                ", uvi=" + uvi +
                '}';
    }
}
