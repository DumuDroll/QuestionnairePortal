package com.dddd.questionnaireportal.database.entity;

import com.dddd.questionnaireportal.common.enums.Type;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "fields")
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String label;

    private String ui_id;

    private String positionTop;

    private String positionLeft;

    private boolean required;

    private boolean active;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @OneToMany(mappedBy = "field", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FieldsOption> options;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Response> responses;

    public Field(String label, boolean required, boolean active, Type type) {
        this.label = label;
        this.required = required;
        this.active = active;
        this.type = type;
    }

    public Field() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<FieldsOption> getOptions() {
        return options;
    }

    public void setOptions(List<FieldsOption> options) {
        this.options = options;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public String getUi_id() {
        return ui_id;
    }

    public void setUi_id(String ui_id) {
        this.ui_id = ui_id;
    }

    public String getPositionTop() {
        return positionTop;
    }

    public void setPositionTop(String positionTop) {
        this.positionTop = positionTop;
    }

    public String getPositionLeft() {
        return positionLeft;
    }

    public void setPositionLeft(String positionLeft) {
        this.positionLeft = positionLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return id == field.id && required == field.required && active == field.active && Objects.equals(label, field.label) && type == field.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
