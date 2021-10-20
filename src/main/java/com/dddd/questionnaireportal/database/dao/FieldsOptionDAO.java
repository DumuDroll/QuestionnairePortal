package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.database.entity.FieldsOption;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class FieldsOptionDAO extends GenericDAO<FieldsOption>{
    public FieldsOptionDAO(){
        super(FieldsOption.class);
    }
}