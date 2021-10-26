package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.ResponseService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.UUID;

@ManagedBean
@ViewScoped
public class ResponsesAddMB {

    private List<Field> fields;

    @PostConstruct
    public void init() {
        fields = FieldService.findAll();
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void save() {
        UUID responsePerUser = UUID.randomUUID();
        Response response;
        for (Field field : fields) {
            response = new Response();
            response.setField(field);
            response.setLabel(field.getLabel());
            response.setResponse(field.getResponse());
            response.setResponsePerUser(responsePerUser);
            field.setResponse(null);
            ResponseService.createResponse(response);
        }
    }
}
