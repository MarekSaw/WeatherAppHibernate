package Proccesing;

import java.time.LocalDate;
import java.util.Arrays;

public class Daily {
    private int dt;
    private int sunrise;
    private int sunset;
    private Temp temp;
    private FeelsLike feels_like;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double wind_speed;
    private int wind_deg;
    private Weather[] weather;
    private int clouds;
    private int pop;
    private double rain;
    private double uvi;

    public int getDt() {
        return dt;
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

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public void setFeels_like(FeelsLike feels_like) {
        this.feels_like = feels_like;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setDew_point(double dew_point) {
        this.dew_point = dew_point;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setWind_deg(int wind_deg) {
        this.wind_deg = wind_deg;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
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
    public String               toString() {
        return "Daily{" +
                "dt=" + LocalDate.ofEpochDay(dt/(3600*24)) +
                ", temp=" + ((temp.getMax() + temp.getMin()) / 2) +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", wind_speed=" + wind_speed +
                ", wind_deg=" + wind_deg +
                '}';
    }
}
