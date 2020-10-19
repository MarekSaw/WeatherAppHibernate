package com.marek.weatherapp.forecastcache.entities;

import com.marek.weatherapp.repositories.model.WeatherSource;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ForecastEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private WeatherForecastEntity weatherForecast;
    private WeatherSource source;
    private String localization;
    private LocalDate forecastDate;
    private LocalDateTime forecastAcquiredDate;

    public ForecastEntity() { }

    public ForecastEntity(WeatherForecastEntity weatherForecast, WeatherSource source, String localization, LocalDate date) {
        this.weatherForecast = weatherForecast;
        this.source = source;
        this.localization = localization;
        this.forecastDate = date;
        this.forecastAcquiredDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getLocalization() {
        return localization;
    }

    public WeatherForecastEntity getWeatherForecast() {
        return weatherForecast;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public LocalDateTime getForecastAcquiredDate() {
        return forecastAcquiredDate;
    }

    public WeatherSource getSource() {
        return source;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public void setWeatherForecast(WeatherForecastEntity weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    public void setForecastAcquiredDate(LocalDateTime forecastAcquiredDate) {
        this.forecastAcquiredDate = forecastAcquiredDate;
    }

    public void setSource(WeatherSource source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ForecastEntity{" +
                "id=" + id +
                ", weatherForecast=" + weatherForecast +
                ", source=" + source +
                ", localization='" + localization + '\'' +
                ", forecastDate=" + forecastDate +
                ", forecastAcquiredDate=" + forecastAcquiredDate +
                '}';
    }
}
