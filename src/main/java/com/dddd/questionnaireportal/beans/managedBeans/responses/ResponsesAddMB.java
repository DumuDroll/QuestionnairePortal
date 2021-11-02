package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.ResponseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean
@ViewScoped
public class ResponsesAddMB {

    private static final Logger logger = LogManager.getLogger();

    private List<Response> responses;

    @PostConstruct
    public void init() {
        List<Field> fields = FieldService.findAll();
        populateResponses(fields);
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public void save() {
        ResponseService.saveResponsesAndSendThemViaWebsocket(responses);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/success");
        }catch (Exception e){
            logger.catching(e);
        }
    }

    public void populateResponses(List<Field> fields) {
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
}
