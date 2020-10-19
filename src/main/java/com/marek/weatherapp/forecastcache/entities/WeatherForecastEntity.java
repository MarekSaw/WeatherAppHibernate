package com.marek.weatherapp.forecastcache.entities;

import javax.persistence.*;

@Entity
public class WeatherForecastEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double temperature;
    private double pressure;
    private double humidity;
    @Column(name = "wind_speed")
    private double windSpeed;
    @Column(name = "wind_deg")
    private double windDeg;

    public WeatherForecastEntity() {
    }

    public WeatherForecastEntity(double temperature, double pressure, double humidity, double windSpeed, double windDeg) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
    }

    public long getId() {
        return id;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDeg(double windDeg) {
        this.windDeg = windDeg;
    }

    @Override
    public String toString() {
        return "WeatherForecastEntity{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDeg +
                '}';
    }
}
