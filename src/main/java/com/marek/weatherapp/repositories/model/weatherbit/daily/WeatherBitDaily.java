package com.marek.weatherapp.repositories.model.weatherbit.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherBitDaily {

    @JsonProperty("temp")
    private double temperature;
    @JsonProperty("pres")
    private double pressure;
    @JsonProperty("rh")
    private double humidity;
    @JsonProperty("wind_spd")
    private double windSpeed;
    @JsonProperty("wind_dir")
    private double windDeg;
    @JsonProperty("valid_date")
    private String date;

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

    public String getDate() {
        return date;
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

    public void setDate(String date) {
        this.date = date;
    }

}
