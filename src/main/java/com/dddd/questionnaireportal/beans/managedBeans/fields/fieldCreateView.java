package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.database.service.impl.FieldServiceImpl;
import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@RequestScoped
public class fieldCreateView {

    private final FieldServiceImpl fieldService = new FieldServiceImpl();

    private String label;
    private String selectedType;
    private String options;
    private boolean required;
    private boolean active;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
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

    public void viewProducts() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        PrimeFaces.current().dialog().openDynamic("viewCreateField", options, null);
    }

    public void Save(){

    }

}
