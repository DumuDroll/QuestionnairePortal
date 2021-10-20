package com.dddd.questionnaireportal.database.service.impl;


import com.dddd.questionnaireportal.database.dao.FieldsOptionDAO;
import com.dddd.questionnaireportal.database.entity.FieldsOption;
import com.dddd.questionnaireportal.database.service.FieldsOptionService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="FieldsOptionService")
public class FieldsOptionServiceImpl implements FieldsOptionService {

    @ManagedProperty("#{fieldsOptionDAO}")
    private FieldsOptionDAO fieldsOptionDAO;

    public FieldsOptionDAO getFieldsOptionDAO() {
        return fieldsOptionDAO;
    }

    public void setFieldsOptionDAO(FieldsOptionDAO fieldsOptionDAO) {
        this.fieldsOptionDAO = fieldsOptionDAO;
    }

    public void createFieldsOption(FieldsOption fieldsOption) {
        fieldsOptionDAO.beginTransaction();
        fieldsOptionDAO.save(fieldsOption);
        fieldsOptionDAO.commitAndCloseTransaction();
    }

    public void updateFieldsOption(FieldsOption fieldsOption) {
        fieldsOptionDAO.beginTransaction();
        FieldsOption persistedFieldsOption = fieldsOptionDAO.find(fieldsOption.getId());
        persistedFieldsOption.setOption(fieldsOption.getOption());
        fieldsOptionDAO.update(persistedFieldsOption);
        fieldsOptionDAO.commitAndCloseTransaction();
    }

}
