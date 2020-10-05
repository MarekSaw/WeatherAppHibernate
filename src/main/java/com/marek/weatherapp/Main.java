package com.marek.weatherapp;

import com.marek.weatherapp.entities.ForecastEntity;
import com.marek.weatherapp.entities.WeatherForecastEntity;
import com.marek.weatherapp.forecastcache.ForecastDao;
import com.marek.weatherapp.forecastcache.WeatherForecastDao;
import com.marek.weatherapp.proccesing.JsonProcessing;
import com.marek.weatherapp.repositories.model.WeatherSource;
import com.marek.weatherapp.repositories.model.openweather.OpenWeatherRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        //SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();
        //sessionFactory.getCurrentSession();




        OpenWeatherRepository owr = new OpenWeatherRepository(OpenWeatherRepository.readKey());
        ForecastDao wfd = new ForecastDao();
        ForecastEntity forecast = new ForecastEntity();

        WeatherForecastEntity forecastForCity1 = owr.getForecast("London");
        WeatherForecastEntity forecastForCity2 = owr.getForecast("London");

        System.out.println(forecastForCity1);
        System.out.println(forecastForCity2);






        /*Scanner input = new Scanner(System.in);
        System.out.println("Witaj, jeżeli chcesz uzyskać informacje o pogodzie na podstawie nazwy miasta wpisz 1, jeżeli koordynatów to 2");
        switch (input.nextInt()) {
            case 1:
                System.out.println("Podaj nazwę miasta");
                String city = input.next();
                System.out.println("Podaj datę w formacie rok, miesiac, dzien jako liczby całkowite kolejno zatwierdzając przyciskiem enter");
                System.out.println("Jeżeli nie chcesz podawać wpisz 0");
                int year = input.nextInt();
                if(year == 0){
                    System.out.println(owr.getForecast(city));
                }else{
                    System.out.println(owr.getForecast(city, LocalDate.of(year,input.nextInt(),input.nextInt())));
                }
                break;
            case 2:
                System.out.println("Podaj koordynaty miasta w kolejności szerokość, długość");
                double lat = input.nextDouble();
                double lon = input.nextDouble();
                System.out.println("Podaj datę w formacie rok, miesiac, dzien jako liczby całkowite kolejno zatwierdzając przyciskiem enter");
                System.out.println("Jeżeli nie chcesz podawać daty wciśnij enter");
                int year1 = input.nextInt();
                if(year1 == 0){
                    System.out.println(owr.getForecast(lat, lon));
                }else{
                    System.out.println(owr.getForecast(lat, lon,LocalDate.of(year1,input.nextInt(),input.nextInt())));
                }
                break;*/
        }


    }



