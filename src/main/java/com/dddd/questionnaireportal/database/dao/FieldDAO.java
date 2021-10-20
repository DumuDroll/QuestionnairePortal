package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.database.entity.Field;

import java.util.List;

public interface FieldDAO extends DAO<Field>{
    List<Field> findAll();
    }
