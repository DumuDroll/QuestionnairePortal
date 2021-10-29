package com.dddd.questionnaireportal.common.util.hibernate;

import com.dddd.questionnaireportal.database.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/QPortal";
    private static final String USER = "postgres";
    private static final String PASS = "root";
    private static final String SHOW_SQL = "true";
    private static final String CURRENT_SESSION_CONTEXT_CLASS = "thread";
    private static final String HBM2DDL_AUTO = "update";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER);
                settings.put(Environment.PASS, PASS);
                settings.put(Environment.SHOW_SQL, SHOW_SQL);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, CURRENT_SESSION_CONTEXT_CLASS);
                settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Field.class);
                configuration.addAnnotatedClass(FieldsOption.class);
                configuration.addAnnotatedClass(Response.class);
                configuration.addAnnotatedClass(UserActivation.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
