package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.emf.EMF;
import com.dddd.questionnaireportal.database.entity.Field;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class FieldDAO {
    private static final EntityManager em = EMF.createEntityManager();

    public static void save(Field entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void update(Field entity) {
        try {
            em.getTransaction().begin();
            Field persistedEntity = em.find(Field.class, entity.getId());
            persistedEntity.setActive(entity.isActive());
            persistedEntity.setType(entity.getType());
            persistedEntity.setRequired(entity.isRequired());
            persistedEntity.setLabel(entity.getLabel());
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
            em.remove(em.getReference(Field.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static List<Field> findAll() {
        List<Field> fields = new ArrayList<>();
        try {
            em.getTransaction().begin();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Field.class));
            fields = em.createQuery(cq).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
        return fields;
    }
}
