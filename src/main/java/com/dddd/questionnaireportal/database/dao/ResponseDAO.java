package com.dddd.questionnaireportal.database.dao;


import com.dddd.questionnaireportal.common.emf.EMF;
import com.dddd.questionnaireportal.database.entity.Response;

import javax.persistence.EntityManager;

public class ResponseDAO{
    private static final EntityManager em = EMF.createEntityManager();

    public static void save(Response entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void update(Response entity) {
        try {
            em.getTransaction().begin();
            Response persistedEntity = em.find(Response.class, entity.getId());
            persistedEntity.setResponse(entity.getResponse());
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
            em.remove(em.getReference(Response.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
