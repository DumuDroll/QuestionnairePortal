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
import java.util.List;

@ManagedBean
@ViewScoped
public class FieldListMB {


    private Field selectedField;
    private List<Field> fields;
    private String options;


    @PostConstruct
    public void init() {
        this.fields = FieldService.findAll();
    }


    public String getOptions() {
        if (selectedField.getId() != 0 && selectedField.getOptions() != null) {
            return takeOptions();
        }
        return options;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void setOptions(String options) {
        this.options = options;
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

    public void deleteField() {
        FieldService.deleteField(selectedField.getId());
        fields.remove(selectedField);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:fields");
    }

    public void openNew() {
        selectedField = new Field();
        options=null;
    }

    public void save() {
        boolean optionsNeeded = selectedField.getType().equals(Type.COMBOBOX) || selectedField.getType().equals(Type.RADIO_BUTTON);
        if (selectedField.getId() == 0) {
            if (optionsNeeded) {
                selectedField.setOptions(new ArrayList<>());
                addOptionsToField();
            }
            FieldService.createField(selectedField);
            fields.add(selectedField);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field Added"));
        } else {
            FieldsOptionService.deleteOptionsByField(selectedField);
            selectedField.setOptions(new ArrayList<>());
            if (optionsNeeded) {
                addOptionsToField();
            }
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
