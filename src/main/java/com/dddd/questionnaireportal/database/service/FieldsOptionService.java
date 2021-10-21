package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.FieldsOptionDAO;
import com.dddd.questionnaireportal.database.entity.FieldsOption;

public class FieldsOptionService {

    public void createFieldsOption(FieldsOption fieldsOption) {
        FieldsOptionDAO.save(fieldsOption);
    }

    public void updateFieldsOption(FieldsOption fieldsOption) {
        FieldsOptionDAO.update(fieldsOption);
    }
}
