package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.entity.ResponsePerUser;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.ResponsePerUserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ResponsesAddMB {

    private List<Response> responses;
    private List<Field> fields;

    @PostConstruct
    public void init() {
        fields = FieldService.findAll();
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void save() {
        responses = new ArrayList<>();
        ResponsePerUser responsePerUser = new ResponsePerUser();
        for (Field field : fields) {
            Response response = new Response();
            response.setField(field);
            response.setLabel(field.getLabel());
            response.setResponse(field.getResponse());
            response.setResponsePerUser(responsePerUser);
            field.setResponse(null);
            responses.add(response);
        }
        responsePerUser.setResponses(responses);
        ResponsePerUserService.createResponsePerUser(responsePerUser);
    }
}
