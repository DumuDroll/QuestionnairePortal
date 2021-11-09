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

    @SuppressWarnings("deprecation")
    public static User findByEmail(String email) {
        User result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.like(Constants.EMAIL, email));
            result = (User) criteria.uniqueResult();
        } catch (Exception e) {
            logger.catching(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

}
