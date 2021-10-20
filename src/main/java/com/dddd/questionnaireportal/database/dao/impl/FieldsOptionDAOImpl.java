package com.dddd.questionnaireportal.database.dao.impl;

import com.dddd.questionnaireportal.common.emf.EMF;
import com.dddd.questionnaireportal.database.dao.FieldsOptionDAO;
import com.dddd.questionnaireportal.database.entity.FieldsOption;

import javax.persistence.EntityManager;

public class FieldsOptionDAOImpl implements FieldsOptionDAO {

    private final EntityManager em = EMF.createEntityManager();

    @Override
    public void save(FieldsOption entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(FieldsOption entity) {
        try {
            em.getTransaction().begin();
            FieldsOption persistedEntity = em.find(FieldsOption.class, entity.getId());
            persistedEntity.setOption(entity.getOption());
            em.merge(persistedEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) {
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(FieldsOption.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
