package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.util.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.Field;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.ArrayList;
import java.util.List;

public class FieldDAO {

    private static final Logger logger = LogManager.getLogger();

    public static void delete(int id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(session.getReference(Field.class, id));
            transaction.commit();
            session.close();
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

    @SuppressWarnings("deprecation")
    public static List<Field> findAll() {
        List<Field> fields = new ArrayList<>();
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Field.class);
            criteria.createAlias("options", "o", JoinType.LEFT_OUTER_JOIN);
            criteria.addOrder(Order.asc("id"));
            criteria.addOrder(Order.asc("o.id"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            fields = criteria.list();
            transaction.commit();
            session.close();
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
        return fields;
    }

    @SuppressWarnings("deprecation")
    public static List<Field> findAllActive() {
        List<Field> fields = new ArrayList<>();
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Field.class);
            criteria.createAlias("options", "o", JoinType.LEFT_OUTER_JOIN);
            criteria.addOrder(Order.asc("id"));
            criteria.addOrder(Order.asc("o.id"));
            criteria.add(Restrictions.eq("active", true));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            fields = criteria.list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.catching(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return fields;
    }
}
