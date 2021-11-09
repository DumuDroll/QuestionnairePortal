package com.dddd.questionnaireportal.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "fieldDimensions")
public class FieldUiDimensions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ui_id;

    private String positionTop;

    private String positionTopForCollision;

    private String positionLeft;

    private String positionLeftForCollision;

    private String width;

    private String height;

    @OneToOne(mappedBy = "fieldUiDimensions")
    private Field field;

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

    public String getPositionTopForCollision() {
        return positionTopForCollision;
    }

    public void setPositionTopForCollision(String positionTopForCollision) {
        this.positionTopForCollision = positionTopForCollision;
    }

    public String getPositionLeft() {
        return positionLeft;
    }

    public void setPositionLeft(String positionLeft) {
        this.positionLeft = positionLeft;
    }

    public String getPositionLeftForCollision() {
        return positionLeftForCollision;
    }

    public void setPositionLeftForCollision(String positionLeftForCollision) {
        this.positionLeftForCollision = positionLeftForCollision;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
