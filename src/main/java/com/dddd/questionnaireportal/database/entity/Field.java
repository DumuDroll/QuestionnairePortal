package com.dddd.questionnaireportal.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;
    private boolean required;
    private boolean active;
    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FieldsOption> options;

    @ManyToMany(mappedBy = "fields")
    private Set<Response> responses = new HashSet<>();

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

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }
}
