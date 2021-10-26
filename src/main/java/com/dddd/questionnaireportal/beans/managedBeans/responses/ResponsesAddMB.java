package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.beans.managedBeans.webSocket.MessageSender;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.ResponseService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ManagedBean
@ViewScoped
public class ResponsesAddMB {

    private List<Response> responses;

    @PostConstruct
    public void init() {
        List<Field> fields = FieldService.findAll();
        responses = new ArrayList<>();
        if (fields != null) {
            for (Field field : fields) {
                Response response = new Response();
                response.setField(field);
                response.setLabel(field.getLabel());
                responses.add(response);
            }
        }
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public void save() {
        UUID responsePerUser = UUID.randomUUID();
        responses.forEach(response -> {
            response.setResponsePerUser(responsePerUser);
            ResponseService.createResponse(response);
        });
        MessageSender.sendMessage("test");
    }
}
