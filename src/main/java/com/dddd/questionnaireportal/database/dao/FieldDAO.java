package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.database.entity.Field;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class FieldDAO extends GenericDAO<Field> {
    public FieldDAO(){
        super(Field.class);
    }
}
