package com.dddd.questionnaireportal.database.service.impl;


import com.dddd.questionnaireportal.database.dao.FieldDAO;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.service.FieldService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

public class FieldServiceImpl implements FieldService {

    private final FieldDAO fieldDAO = new FieldDAO();

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

    public void deleteField(int id){
        fieldDAO.beginTransaction();
        Field persistedFieldWithIdOnly = fieldDAO.findReferenceOnly(id);
        fieldDAO.delete(persistedFieldWithIdOnly);
        fieldDAO.commitAndCloseTransaction();
    }

    public List<Field> findAll(){
        fieldDAO.beginTransaction();
        List<Field> fields = fieldDAO.findAll();
        fieldDAO.closeTransaction();
        return fields;
    }
}
