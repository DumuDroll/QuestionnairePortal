package com.dddd.questionnaireportal.common.util.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {

    private static final Logger logger = LogManager.getLogger();

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Map<String,String> jdbcUrlSettings = new HashMap<>();
                String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
                if (null != jdbcDbUrl) {
                    jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
                }

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                        configure("hibernate.cfg.xml").
                        applySettings(jdbcUrlSettings).
                        build();
                sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                logger.catching(e);
            }
        }
        return sessionFactory;
    }
}
