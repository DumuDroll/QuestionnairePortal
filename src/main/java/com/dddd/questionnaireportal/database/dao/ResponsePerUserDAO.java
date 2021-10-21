package com.dddd.questionnaireportal.database.dao;


import com.dddd.questionnaireportal.common.emf.EMF;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.entity.ResponsePerUser;

import javax.persistence.EntityManager;

public class ResponsePerUserDAO{

    private static final EntityManager em = EMF.createEntityManager();

    public static void save(ResponsePerUser entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
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

