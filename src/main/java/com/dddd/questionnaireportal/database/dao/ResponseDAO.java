package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.util.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

public class ResponseDAO {

    private static final Logger logger = LogManager.getLogger();

    public static void update(Response entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
            logger.catching(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @SuppressWarnings("deprecation")
    public static List<Response> findAll() {
        List<Response> responses = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Response.class);
            criteria.addOrder(Order.asc("id"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            responses = criteria.list();
        } catch (Exception e) {
            logger.catching(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return responses;
    }
}
