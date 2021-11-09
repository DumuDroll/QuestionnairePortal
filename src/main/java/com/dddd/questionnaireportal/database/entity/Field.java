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

    private boolean required;

    private boolean active;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private FieldUiDimensions fieldUiDimensions = new FieldUiDimensions();

    @OneToMany(mappedBy = "field", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FieldsOption> options;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Response> responses;

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

    public FieldUiDimensions getFieldUiDimensions() {
        return fieldUiDimensions;
    }

    public void setFieldUiDimensions(FieldUiDimensions fieldUiDimensions) {
        this.fieldUiDimensions = fieldUiDimensions;
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
