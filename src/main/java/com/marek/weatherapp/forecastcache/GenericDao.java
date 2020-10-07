package com.marek.weatherapp.forecastcache;

import com.marek.weatherapp.config.DatabaseConfig;
import com.marek.weatherapp.forecastcache.entities.WeatherForecastEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GenericDao <T> {
    private T genericClass;

    public void addForecast(T forecast){
        try (Session session = DatabaseConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(forecast);
            transaction.commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> getForecasts() {
        try (Session session = DatabaseConfig.getSessionFactory().openSession()) {
            return (List<T>) session.createQuery("select f from " + genericClass.getClass().getName() + " f").list();
        }
    }
}
