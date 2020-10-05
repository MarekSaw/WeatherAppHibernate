package com.marek.weatherapp.config;

import com.marek.weatherapp.entities.ForecastEntity;
import com.marek.weatherapp.entities.WeatherForecastEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class DatabaseConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Properties getSettings() {
        Properties settings = new Properties();

        /*settings.put(Environment.URL, "jdbc:mysql://localhost:3306/forecast?serverTimezone=EST");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "kursJava2020");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
        // optional in most cases
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");*/

        settings.put(Environment.URL, "jdbc:h2:file:./test.db");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
        // optional in most cases
        //settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        //settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");

        return settings;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        configuration.setProperties(getSettings());

        configuration.addAnnotatedClass(WeatherForecastEntity.class);
        configuration.addAnnotatedClass(ForecastEntity.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
