package com.marek.weatherapp.forecastcache;

import com.marek.weatherapp.config.DatabaseConfig;
import com.marek.weatherapp.entities.ForecastEntity;
import com.marek.weatherapp.entities.WeatherForecastEntity;
import com.marek.weatherapp.repositories.model.WeatherSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class ForecastDao {

    public void addForecast(ForecastEntity forecast){
        try (Session session = DatabaseConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(forecast);
            transaction.commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ForecastEntity> getForecasts() {
        try (Session session = DatabaseConfig.getSessionFactory().openSession()) {
            return session.createQuery("select f from ForecastEntity f").list();
        }
    }

    @SuppressWarnings("unchecked")
    public ForecastEntity findWeatherForecastForLocalization(WeatherSource source, String localization, LocalDate forecastDate) {
        LocalDateTime todayMidnight = LocalDate.now().atStartOfDay();
        try (Session session = DatabaseConfig.getSessionFactory().openSession()) {
            List<ForecastEntity> forecasts = session.createQuery("select f from ForecastEntity f " +
                    "where f.source = :source and f.localization = :localization and f.forecastDate = :forecastDate " +
                    "and f.forecastAcquiredDate > :todayMidnight")
                    .setParameter("source", source)
                    .setParameter("localization", localization)
                    .setParameter("forecastDate", forecastDate)
                    .setParameter("todayMidnight", todayMidnight)
                    .list();

            return forecasts.stream().findFirst().orElse(null);
        }
    }
}
