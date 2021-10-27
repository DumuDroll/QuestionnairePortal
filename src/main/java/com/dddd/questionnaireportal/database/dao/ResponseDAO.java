package com.dddd.questionnaireportal.database.dao;


import com.dddd.questionnaireportal.common.util.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.Response;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.List;

public class ResponseDAO {

    public static void update(Response entity) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Response persistedEntity = session.find(Response.class, entity.getId());
            persistedEntity.setResponse(entity.getResponse());
            persistedEntity.setLabel(entity.getLabel());
            session.merge(persistedEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.remove(session.getReference(Response.class, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static List<Response> findAll() {
        List<Response> responses = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Response.class);
            responses = criteria.addOrder(Order.asc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return responses;
    }
    @SuppressWarnings("deprecation")
    public static long findNumberOfRows() {
        long count = 0L;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Response.class);
            count = (long)criteria.setProjection(Projections.countDistinct("responsePerUser")).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return count;
    }

    @SuppressWarnings("deprecation")
    public static long findNumberOfColumns() {
        long count = 0L;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Response.class);
            count = (long) criteria.setProjection(Projections.countDistinct("label")).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return count;
    }
}
