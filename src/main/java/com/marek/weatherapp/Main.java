package com.marek.weatherapp;

import com.marek.weatherapp.entities.WeatherForecastEntity;
import com.marek.weatherapp.repositories.WeatherRepository;
import com.marek.weatherapp.repositories.model.WeatherSource;
import com.marek.weatherapp.repositories.model.openweather.OpenWeatherRepository;
import com.marek.weatherapp.repositories.model.weatherbit.WeatherBitRepository;
import org.apache.commons.cli.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;

public class Main {
    private static final WeatherRepository WEATHER_BIT = new WeatherBitRepository(WeatherRepository.readKey(WeatherSource.WEATHER_BIT));
    private static final WeatherRepository OPEN_WEATHER = new OpenWeatherRepository(WeatherRepository.readKey(WeatherSource.OPEN_WEATHER));


    public static void main(String[] args) {

        callWithCommandLineArguments(args);

    }

    private static void callWithCommandLineArguments(String[] args) {
        CommandLine commandLine = parseCommandLine(args);
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        WeatherForecastEntity weatherForecastEntity;

        if (Objects.nonNull(commandLine)) {
            // get arguments from command line
            String city = commandLine.hasOption("c") ? commandLine.getOptionValue("c") : null;
            Double lat = commandLine.hasOption("lat") ? Double.valueOf(commandLine.getOptionValue("lat")) : null;
            Double lon = commandLine.hasOption("lon") ? Double.valueOf(commandLine.getOptionValue("lon")) : null;
            LocalDate date = commandLine.hasOption("d") ? LocalDate.parse(commandLine.getOptionValue("d")) : tomorrow;

            // check if localization is given properly
            if (city == null && (lat == null || lon == null)) {
                throw new InputMismatchException("Localization has been given incorrectly. City or both coordinates must be given");
            }

            // get average forecast for given localization and date
            if (city != null) {
                weatherForecastEntity = getAverageForecast(city, date);
            } else {
                weatherForecastEntity = getAverageForecast(lat, lon, date);
            }

            showForecast(weatherForecastEntity, date);

        }

    }

    private static CommandLine parseCommandLine(String[] args) {
        try {
            CommandLineParser parser = new GnuParser();
            Options options = new Options();
            options.addOption("d", "date", true, "Date to get forecast for");
            options.addOption("c", "city", true, "City to get forecast for");
            options.addOption("lat", "latitude", true, "Latitude coordinate to get forecast for");
            options.addOption("lon", "longitude", true, "Longitude coordinate to get forecast for");
            return parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("An error occurred during argument parsing");
            return null;
        }
    }

    private static WeatherForecastEntity getAverageForecast(String city, LocalDate date) {
        WeatherForecastEntity weatherBit = WEATHER_BIT.getForecast(city, date);
        WeatherForecastEntity openWeather = OPEN_WEATHER.getForecast(city, date);

        return new WeatherForecastEntity(
                (weatherBit.getTemperature() + openWeather.getTemperature()) / 2,
                (weatherBit.getPressure() + openWeather.getPressure()) / 2,
                (weatherBit.getHumidity() + openWeather.getHumidity()) / 2,
                (weatherBit.getWindSpeed() + openWeather.getWindSpeed()) / 2,
                (weatherBit.getWindDeg() + openWeather.getWindDeg()) / 2);
    }

    private static WeatherForecastEntity getAverageForecast(double latitude, double longitude, LocalDate date) {
        WeatherForecastEntity weatherBit = WEATHER_BIT.getForecast(latitude, longitude, date);
        WeatherForecastEntity openWeather = OPEN_WEATHER.getForecast(latitude, longitude, date);

        return new WeatherForecastEntity(
                (weatherBit.getTemperature() + openWeather.getTemperature()) / 2,
                (weatherBit.getPressure() + openWeather.getPressure()) / 2,
                (weatherBit.getHumidity() + openWeather.getHumidity()) / 2,
                (weatherBit.getWindSpeed() + openWeather.getWindSpeed()) / 2,
                (weatherBit.getWindDeg() + openWeather.getWindDeg()) / 2);
    }

    private static void showForecast(WeatherForecastEntity wfe, LocalDate date) {
        System.out.println("Forecast for date: " + date);
        System.out.println("Temperature: " + wfe.getTemperature() + " \u00B0C");
        System.out.println("Pressure: " + wfe.getPressure() + " hPa");
        System.out.println("Humidity: " + wfe.getHumidity() + " %");
        System.out.println("Wind speed: " + wfe.getWindSpeed() + " m/s");
        System.out.println("Wind direction: " + wfe.getWindDeg() + " \u00B0");
    }

}



