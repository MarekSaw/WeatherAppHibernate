package com.marek.weatherapp;

import com.marek.weatherapp.entities.WeatherForecastEntity;
import com.marek.weatherapp.repositories.model.openweather.OpenWeatherRepository;
import org.apache.commons.cli.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;

public class Main {


    public static void main(String[] args) {

        OpenWeatherRepository owr = new OpenWeatherRepository(OpenWeatherRepository.readKey());

//        WeatherForecastEntity forecastForCity1 = owr.getForecast("London");
        WeatherForecastEntity forecastForCity2 = owr.getForecast(50.041187, 21.999121, LocalDate.now().plusDays(2));
//        System.out.println(forecastForCity1);
        System.out.println(forecastForCity2);
        callWithCommandLineArguments(owr, args);


    }

    private static void callWithCommandLineArguments(OpenWeatherRepository openWeatherRepository, String[] args) {
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

            // get forecast for given localization and date
            if (city != null) {
                weatherForecastEntity = openWeatherRepository.getForecast(city, date);
            } else {
                weatherForecastEntity = openWeatherRepository.getForecast(lat, lon, date);
            }

            System.out.println(weatherForecastEntity + " for date " + date);

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


}



