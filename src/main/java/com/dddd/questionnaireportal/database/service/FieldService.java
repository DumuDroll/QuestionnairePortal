package com.dddd.questionnaireportal.database.service;


import com.dddd.questionnaireportal.database.entity.Field;

public interface FieldService {
    void createField(Field field);
    void updateField(Field field);
    void deleteField(int id);
}
