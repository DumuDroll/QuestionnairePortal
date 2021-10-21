package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Type;
import com.dddd.questionnaireportal.database.service.FieldService;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@ViewScoped
public class FieldCreateMB {

    private Field selectedField;
    private Field field;
    private String label;
    private Type type;
    private String options;
    private boolean required;
    private boolean active;

    public Type[] getTypes() {
        return Type.values();
    }

    public Field getField() {
        return field;
    }

    public Field getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(Field selectedField) {
        this.selectedField = selectedField;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void openNew(){
        this.selectedField= new Field();
    }

    public void viewCreateDialog() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        PrimeFaces.current().dialog().openDynamic("fieldCreate", options, null);
    }

    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", " Always Bet on Prime!");

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void save() {
        Field field = new Field(label, required, active, type);
        FieldService.createField(field);

        PrimeFaces.current().executeScript("PF('addFieldDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

}
