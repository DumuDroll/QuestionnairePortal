package com.dddd.questionnaireportal.database.service.impl;


import com.dddd.questionnaireportal.database.dao.impl.FieldDAOImpl;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.service.FieldService;

import java.util.List;

public class FieldServiceImpl implements FieldService {

    private final FieldDAOImpl fieldDAO = new FieldDAOImpl();

    public void createField(Field field){
        fieldDAO.save(field);
    }

    public void updateField(Field field){
        fieldDAO.update(field);
    }

    public void deleteField(int id){
        fieldDAO.delete(id);
    }

    public List<Field> findAll(){
        return fieldDAO.findAll();
    }
}
