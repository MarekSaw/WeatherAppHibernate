package Proccesing;

import java.util.Arrays;

public class Current {
    private int dt;
    private int sunrise;
    private int sunset;
    private double temp;
    private double feels_like;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double uvi;
    private int clouds;
    private int visibility;
    private double wind_speed;
    private int wind_deg;
    private Weather[] weather;

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setFeels_like(double feels_like) {
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

    public void setUvi(double uvi) {
        this.uvi = uvi;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
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

    @Override
    public String toString() {
        return "Current{" +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", wind_speed=" + wind_speed +
                ", wind_deg=" + wind_deg +
                '}';
    }
}