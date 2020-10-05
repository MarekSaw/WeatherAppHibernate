package com.marek.weatherapp.repositories.model.openweather.daily;

public class ForecastDailyFeelsLike {
    private double day;
    private double night;
    private double eve;
    private double morn;

    public double getDay() {
        return day;
    }

    public double getNight() {
        return night;
    }

    public double getEve() {
        return eve;
    }

    public double getMorn() {
        return morn;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }

    @Override
    public String toString() {
        return "FeelsLike{" +
                "day=" + day +
                ", night=" + night +
                ", eve=" + eve +
                ", morn=" + morn +
                '}';
    }
}

