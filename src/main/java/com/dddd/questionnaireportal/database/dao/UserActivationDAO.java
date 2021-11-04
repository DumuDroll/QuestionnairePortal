package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.util.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.UserActivation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserActivationDAO {

    private static final Logger logger = LogManager.getLogger();

    @SuppressWarnings("deprecation")
    public static UserActivation findByUUID(String uuid) {
        UserActivation result = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(UserActivation.class)
                    .add(Restrictions.like("uuid", uuid));
            result = (UserActivation) criteria.uniqueResult();
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
        return result;
    }
}
