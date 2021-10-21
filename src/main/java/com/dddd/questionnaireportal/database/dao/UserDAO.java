package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.emf.EMF;
import com.dddd.questionnaireportal.database.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserDAO{

    private static final EntityManager em = EMF.createEntityManager();

    public static User findById(int id) {
        User user = null;
        try {
            em.getTransaction().begin();
            user = em.find(User.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return user;
    }

    public static void save(User entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void update(User entity) {
        try {
            em.getTransaction().begin();
            User persistedEntity = em.find(User.class, entity.getId());
            persistedEntity.setActive(entity.isActive());
            persistedEntity.setEmail(entity.getEmail());
            persistedEntity.setFirstName(entity.getFirstName());
            persistedEntity.setLastName(entity.getLastName());
            persistedEntity.setPassword(entity.getPassword());
            persistedEntity.setPhoneNumber(entity.getPhoneNumber());
            em.merge(persistedEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static User findByEmail(String email) {
        User result = null;
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery(Constants.FIND_BY_EMAIL);
            query.setParameter("email", email);
            result = (User) query.getSingleResult();
            em.getTransaction().commit();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + Constants.FIND_BY_EMAIL);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
