package com.dddd.questionnaireportal.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "options")
@NamedQuery(name="FieldsOption.deleteOptionsById", query = "delete from FieldsOption where field = :field")
public class FieldsOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String option;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
    private Field field;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
