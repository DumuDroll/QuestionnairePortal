package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.service.FieldService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean
@RequestScoped
public class FieldListMB {



    public List<Field> getFieldList() {
        return FieldService.findAll();
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
