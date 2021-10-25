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


    public void save(){
        responses = new ArrayList<>();
        ResponsePerUser responsePerUser = new ResponsePerUser();
        for (Field field: fields){
            Response response = new Response();
            response.setField(field);
            response.setLabel(field.getLabel());
            response.setResponse(field.getResponse());
            response.setResponsePerUser(responsePerUser);
            if(field.getResponses()==null){
                field.setResponses(new ArrayList<>());
            }else{
                field.getResponses().add(response);
            }
            field.setResponse(null);
            responses.add(response);
        }
        responsePerUser.setResponses(responses);
        ResponsePerUserService.createResponsePerUser(responsePerUser);
    }

//    public void populateForm(ComponentSystemEvent event) {
//        HtmlForm form = (HtmlForm) event.getComponent();
//        for (Response response : responses) {
//            switch (response.getType()) {
//                case SINGLE_LINE_TEXT:
//                    UIInput input = new HtmlInputText();
//                    input.setId(Integer.toString(response.getId()));
//                    input.setValueExpression("value",
//                            createValueExpression(
//                                    "#{responsesAddMB.responses}",
//                                    String.class));
//                    form.getChildren().add(input);
//                    responses.add(new Response());
//                    break;
//                case MULTILINE_TEXT:
//                    UIInput inputTA = new HtmlInputTextarea();
//                    inputTA.setId(Integer.toString(response.getId()));
//                    inputTA.setValueExpression("value",
//                            createValueExpression(
//                                    "#{response}",
//                                    String.class));
//                    form.getChildren().add(inputTA);
//                    responses.add(new Response());
//                    break;
//                case RADIO_BUTTON:
//                    UIInput inputRB = new HtmlSelectOneRadio();
//                    inputRB.setId(Integer.toString(response.getId()));
//                    inputRB.setValueExpression("value",
//                            createValueExpression(
//                                    "#{response}",
//                                    String.class));
//                    form.getChildren().add(inputRB);
//                    responses.add(new Response());
//                    break;
//                case CHECKBOX:
//                    UIInput inputCH = new HtmlSelectBooleanCheckbox();
//                    inputCH.setId(Integer.toString(response.getId()));
//                    inputCH.setValueExpression("value",
//                            createValueExpression(
//                                    "#{response}",
//                                    String.class));
//                    form.getChildren().add(inputCH);
//                    responses.add(new Response());
//                    break;
//                case COMBOBOX:
//                    UIInput inputC = new HtmlSelectOneMenu();
//                    inputC.setId(Integer.toString(response.getId()));
//                    inputC.setValueExpression("value",
//                            createValueExpression(
//                                    "#{response}",
//                                    String.class));
//                    form.getChildren().add(inputC);
//                    responses.add(new Response());
//                    break;
//                case DATE:
//                    UIInput inputD = new HtmlInputText();
//                    inputD.setId(Integer.toString(response.getId()));
//                    inputD.setValueExpression("value",
//                            createValueExpression(
//                                    "#{response}",
//                                    String.class));
//                    form.getChildren().add(inputD);
//                    responses.add(new Response());
//                    break;
//            }
//        }
//    }
}
