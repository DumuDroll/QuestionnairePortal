package com.dddd.questionnaireportal.database.dao;


import com.dddd.questionnaireportal.common.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.entity.ResponsePerUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

public class ResponsePerUserDAO {

    public static void save(ResponsePerUser entity) {
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

    public static void delete(int id) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.remove(session.getReference(Response.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static List<ResponsePerUser> findAll() {
        List<ResponsePerUser> responsePerUserList = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(ResponsePerUser.class);
            responsePerUserList = criteria.addOrder(Order.asc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return responsePerUserList;
    }

}

