package com.dddd.questionnaireportal.common.util.hibernate;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static final Logger logger = LogManager.getLogger();

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/QPortal");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.SHOW_SQL, Constants.TRUE);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Field.class);
                configuration.addAnnotatedClass(FieldsOption.class);
                configuration.addAnnotatedClass(Response.class);
                configuration.addAnnotatedClass(UserActivation.class);
                configuration.addAnnotatedClass(Authority.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                logger.catching(e);
            }
        }
        return sessionFactory;
    }
}
