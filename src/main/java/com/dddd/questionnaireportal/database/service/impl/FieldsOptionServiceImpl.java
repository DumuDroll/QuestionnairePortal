package com.dddd.questionnaireportal.database.service.impl;


import com.dddd.questionnaireportal.database.dao.impl.FieldsOptionDAOImpl;
import com.dddd.questionnaireportal.database.entity.FieldsOption;
import com.dddd.questionnaireportal.database.service.FieldsOptionService;

public class FieldsOptionServiceImpl implements FieldsOptionService {

    private final FieldsOptionDAOImpl fieldsOptionDAO = new FieldsOptionDAOImpl();

    public void createFieldsOption(FieldsOption fieldsOption) {
        fieldsOptionDAO.save(fieldsOption);
    }

    public void updateFieldsOption(FieldsOption fieldsOption) {
        fieldsOptionDAO.update(fieldsOption);
    }

}
