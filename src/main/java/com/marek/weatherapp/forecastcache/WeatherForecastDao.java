package com.marek.weatherapp.forecastcache;

import com.marek.weatherapp.config.DatabaseConfig;
import com.marek.weatherapp.entities.WeatherForecastEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


public class WeatherForecastDao {

    public void addForecast(WeatherForecastEntity forecast){
        try (Session session = DatabaseConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(forecast);
            transaction.commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<WeatherForecastEntity> getForecasts() {
        try (Session session = DatabaseConfig.getSessionFactory().openSession()) {
            List<WeatherForecastEntity> forecasts = session.createQuery("select f from WeatherForecastEntity f").list();
            session.close();
            return forecasts;
        }
    }
}
