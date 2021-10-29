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
            logger.catching(e);
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
            logger.catching(e);
        }
        return responses;
    }
}
