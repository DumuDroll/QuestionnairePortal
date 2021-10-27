package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.Field;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FieldsOptionDAO {

    public static void deleteOptionsByField(Field field) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createNamedQuery(Constants.DELETE_OPTIONS_BY_ID);
            query.setParameter("field", field);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
