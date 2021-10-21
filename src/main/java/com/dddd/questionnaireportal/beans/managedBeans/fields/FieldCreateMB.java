package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Type;
import com.dddd.questionnaireportal.database.service.FieldService;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@ViewScoped
public class FieldCreateMB {

    private Field selectedField;
    private Type type;

    public Type[] getTypes() {
        return Type.values();
    }

    public Field getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(Field selectedField) {
        this.selectedField = selectedField;
    }

    public void openNew() {
        this.selectedField = new Field();
    }

    public void save() {
        if (this.selectedField.getId() == 0) {
            FieldService.createField(selectedField);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        } else {
            FieldService.updateField(selectedField);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }
        PrimeFaces.current().executeScript("PF('addFieldDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:fields");
    }

}
