package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.service.FieldService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean
@ViewScoped
public class FieldListMB {

    private List<Field> fields;

    public List<Field> getFields() {
        return fields;
    }

    @PostConstruct
    public void init() {
        this.fields = FieldService.findAll();
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void showDeleteConfirmation() {
        addMessage("Confirmed", "Record deleted");
    }

    public void delete(int fieldId) throws IOException {
        FieldService.deleteField(fieldId);
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
