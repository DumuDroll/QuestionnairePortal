package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.util.hibernate.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaverHelperDAO {

    private static final Logger logger = LogManager.getLogger();

    public static void save(Object entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.catching(e);
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void update(Object entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.catching(e);
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
