package com.dddd.questionnaireportal.beans.managedBeans;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.service.impl.FieldServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean
@RequestScoped
public class FieldListMB {


    private final FieldServiceImpl fieldService = new FieldServiceImpl();

    public List<Field> getFieldList() {
        return fieldService.findAll();
    }

    public void showDeleteConfirmation() {
        addMessage("Confirmed", "Record deleted");
    }

    public void delete(int fieldId) throws IOException {
        fieldService.deleteField(fieldId);
        System.out.println("dsadas");
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
