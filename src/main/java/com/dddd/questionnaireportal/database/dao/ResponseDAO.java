package com.dddd.questionnaireportal.database.dao;


import com.dddd.questionnaireportal.common.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.Response;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResponseDAO {

    public static void save(Response entity) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

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
}
