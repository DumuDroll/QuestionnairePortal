package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.FieldsOption;
import com.dddd.questionnaireportal.database.entity.Type;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.FieldsOptionService;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean
@ViewScoped
public class FieldListMB {


    private Field selectedField;
    private List<Field> fields;
    private String options;
    private boolean optionsRender;

    public String getOptions() {
        if (selectedField.getId()!=0) {
            return takeOptions();
        }
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public List<Field> getFields() {
        return fields;
    }

    public Type[] getTypes() {
        return Type.values();
    }

    public Field getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(Field selectedField) {
        this.selectedField = selectedField;
    }


    @PostConstruct
    public void init() {
        this.fields = FieldService.findAll();
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void deleteField() {
        FieldService.deleteField(selectedField.getId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:fields");
    }

    public void openNew() {
        selectedField = new Field();
    }

    public void save() {
        if (selectedField.getId() == 0) {
            selectedField.setOptions(new ArrayList<>());
            addOptionsToField();
            FieldService.createField(selectedField);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field Added"));
        } else {
            FieldsOptionService.deleteOptionsByField(selectedField);
            selectedField.setOptions(new ArrayList<>());
            addOptionsToField();
            FieldService.updateField(selectedField);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field Updated"));
        }
        PrimeFaces.current().executeScript("PF('manageFieldDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:fields");
    }

    public void addOptionsToField() {
        String[] lines = options.split("\\r?\\n");
        for (String line : lines) {
            line = line.trim();
            FieldsOption fieldsOption = new FieldsOption();
            fieldsOption.setOption(line);
            selectedField.getOptions().add(fieldsOption);
            fieldsOption.setField(selectedField);
        }
    }

    public String takeOptions() {
        List<FieldsOption> fieldsOptionSet = selectedField.getOptions();
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i=fieldsOptionSet.size()-1; i>=0;i--){

        }
        for (FieldsOption fieldsOption : fieldsOptionSet) {
            if (first) {
                first = false;
            } else {
                sb.append(System.lineSeparator());
            }
            sb.append(fieldsOption.getOption());
        }
        return sb.toString();
    }
}
