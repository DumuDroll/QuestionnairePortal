package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.FieldsOptionDAO;
import com.dddd.questionnaireportal.database.entity.Field;

public class FieldsOptionService {

    public static void deleteOptionsByField(Field field) {
        FieldsOptionDAO.deleteOptionsByField(field);
    }
}
