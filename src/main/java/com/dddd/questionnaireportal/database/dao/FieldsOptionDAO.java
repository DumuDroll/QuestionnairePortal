package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.emf.EMF;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.FieldsOption;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.awt.geom.QuadCurve2D;

public class FieldsOptionDAO {
    private static final EntityManager em = EMF.createEntityManager();

    public static void save(FieldsOption entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void update(FieldsOption entity) {
        try {
            em.getTransaction().begin();
            FieldsOption persistedEntity = em.find(FieldsOption.class, entity.getId());
            persistedEntity.setOption(entity.getOption());
            em.merge(persistedEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(FieldsOption.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void deleteOptionsByField(Field field) {
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery(Constants.DELETE_OPTIONS_BY_ID);
            query.setParameter("field", field);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
