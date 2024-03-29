package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.common.enums.Type;
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
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ResponsesAddBean {

    private static final Logger logger = LogManager.getLogger();

    private List<Response> responses;

    @PostConstruct
    public void init() {
        List<Field> fields = FieldService.findAll();
        populateResponses(fields);
    }

    public void save() {
        ResponseService.saveResponsesAndSendThemViaWebsocket(getResponses());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/success");
        } catch (Exception e) {
            logger.catching(e);
        }
    }

    public void populateResponses(List<Field> fields) {
        setResponses(new ArrayList<>());
        if (fields != null) {
            for (Field field : fields) {
                Response response = new Response();
                response.setField(field);
                response.setLabel(field.getLabel());
                getResponses().add(response);
            }
        }
    }

    public boolean singleLineText(Field field){
        return (field.isActive() && field.getType().equals(Type.SINGLE_LINE_TEXT));
    }
    public boolean multiLineText(Field field){
        return (field.isActive() && field.getType().equals(Type.MULTILINE_TEXT));
    }
    public boolean radioButton(Field field){
        return (field.isActive() && field.getType().equals(Type.RADIO_BUTTON));
    }
    public boolean checkbox(Field field){
        return (field.isActive() && field.getType().equals(Type.CHECKBOX));
    }
    public boolean combobox(Field field){
        return (field.isActive() && field.getType().equals(Type.COMBOBOX));
    }
    public boolean date(Field field){
        return (field.isActive() && field.getType().equals(Type.DATE));
    }
    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

}
