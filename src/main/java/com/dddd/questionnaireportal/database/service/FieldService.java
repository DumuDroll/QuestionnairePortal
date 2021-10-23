package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.FieldDAO;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.FieldsOption;

import java.util.List;

public class FieldService {
    public static void createField(Field field){
       FieldDAO.save(field);
    }

    public static void updateField(Field field){
        FieldDAO.update(field);
    }

    public static void deleteField(int id){
        FieldDAO.delete(id);
    }

    public static List<Field> findAll(){
        return FieldDAO.findAll();
    }
}
