package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.hibernate.HibernateUtil;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.FieldsOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.sql.JoinType;

import java.util.ArrayList;
import java.util.List;

public class FieldsOptionDAO {

    private static final Logger logger = LogManager.getLogger();

    @SuppressWarnings("deprecation")
    public static void deleteOptionsByField(Field field) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(FieldsOption.class);
            criteria.addOrder(Order.asc("id"));
            criteria.add(Restrictions.eq("field", field));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<FieldsOption> fields = criteria.list();
            for(FieldsOption fieldsOption: fields){
               session.remove(fieldsOption);
            }
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
}
