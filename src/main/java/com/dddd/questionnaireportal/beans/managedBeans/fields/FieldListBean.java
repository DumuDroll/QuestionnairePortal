package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.FieldsOption;
import com.dddd.questionnaireportal.common.enums.Type;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.FieldsOptionService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class FieldListBean {

    private static final String OPTIONS_REGEX = "\\r?\\n";

    private Field selectedField;
    private List<Field> fields;
    private String options;


    @PostConstruct
    public void init() {
        this.fields = FieldService.findAll();
    }

    public void deleteField() {
        FieldService.deleteField(selectedField.getId());
        fields.remove(selectedField);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field Removed"));
    }

    public void openNew() {
        selectedField = new Field();
        options = null;
    }

    public void save() {
        boolean optionsNeeded = getSelectedField().getType().equals(Type.COMBOBOX)
                || getSelectedField().getType().equals(Type.RADIO_BUTTON);
        if (getSelectedField().getId() == 0) {
            if (optionsNeeded) {
                getSelectedField().setOptions(new ArrayList<>());
                addOptionsToField();
            }
            FieldService.createField(getSelectedField());
            getFields().add(getSelectedField());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field Added"));
        } else {
            FieldsOptionService.deleteOptionsByField(getSelectedField());
            getSelectedField().setOptions(new ArrayList<>());
            if (optionsNeeded) {
                addOptionsToField();
            }
            FieldService.updateField(getSelectedField());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Field Updated"));
        }
    }

    public void addOptionsToField() {
        String[] lines = getOptions(true).split(OPTIONS_REGEX);
        for (String line : lines) {
            line = line.trim();
            FieldsOption fieldsOption = new FieldsOption();
            fieldsOption.setOption(line);
            getSelectedField().getOptions().add(fieldsOption);
            fieldsOption.setField(getSelectedField());
        }
    }

    public String takeOptions() {
        List<FieldsOption> fieldsOptionSet = getSelectedField().getOptions();
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

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOptions() {
        if (selectedField.getId() != 0 && selectedField.getOptions() != null) {
            return takeOptions();
        }
        return options;
    }
    public String getOptions(boolean needOptions){
        return options;
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

}
