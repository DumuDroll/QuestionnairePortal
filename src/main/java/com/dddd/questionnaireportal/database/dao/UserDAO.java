package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    public static void update(User entity) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            User persistedEntity = session.find(User.class, entity.getId());
            persistedEntity.setActive(entity.isActive());
            persistedEntity.setEmail(entity.getEmail());
            persistedEntity.setFirstName(entity.getFirstName());
            persistedEntity.setLastName(entity.getLastName());
            persistedEntity.setPassword(entity.getPassword());
            persistedEntity.setPhoneNumber(entity.getPhoneNumber());
            session.merge(persistedEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.catching(e);
        }
    }

    public static User findById(int id) {
        User user = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            user = session.find(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.catching(e);
        }
        return user;
    }

    @SuppressWarnings("deprecation")
    public static User findByEmail(String email) {
        User result = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.like(Constants.EMAIL, email));
            result = (User) criteria.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.catching(e);
        }
        return result;
    }

}
