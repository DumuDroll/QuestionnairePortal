package com.dddd.questionnaireportal.database.service.impl;


import com.dddd.questionnaireportal.database.dao.FieldDAO;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.service.FieldService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="FieldService")
public class FieldServiceImpl implements FieldService {

    @ManagedProperty("#{fieldDAO}")
    private FieldDAO fieldDAO;

    public FieldDAO getFieldDAO() {
        return fieldDAO;
    }

    public void setFieldDAO(FieldDAO fieldDAO) {
        this.fieldDAO = fieldDAO;
    }

    public void createField(Field field){
        fieldDAO.beginTransaction();
        fieldDAO.save(field);
        fieldDAO.commitAndCloseTransaction();
    }

    public void updateField(Field field){
        fieldDAO.beginTransaction();
        Field persistedField = fieldDAO.find(field.getId());
        persistedField.setActive(field.isActive());
        persistedField.setRequired(field.isRequired());
        persistedField.setLabel(field.getLabel());
        fieldDAO.update(persistedField);
        fieldDAO.commitAndCloseTransaction();
    }

    public void deleteField(Field field){
        fieldDAO.beginTransaction();
        Field persistedFieldWithIdOnly = fieldDAO.findReferenceOnly(field.getId());
        fieldDAO.delete(persistedFieldWithIdOnly);
        fieldDAO.commitAndCloseTransaction();
    }
}
