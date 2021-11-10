package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.dao.FieldDAO;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.FieldUiDimensions;
import com.dddd.questionnaireportal.database.entity.Response;

import java.util.List;

public class FieldService {

    public static void createField(Field field) {
        FieldUiDimensions fieldUiDimensions = new FieldUiDimensions();
        SaverHelperDAO.save(field);
        fieldUiDimensions.setField(field);
        SaverHelperDAO.save(fieldUiDimensions);
    }

    public static void updateField(Field field) {
        Field oldField = FieldDAO.findById(field.getId());
        List<Response> responses = field.getResponses();
        if (responses != null) {
            for (Response response : responses) {
                if (oldField.getLabel().equals(response.getLabel())) {
                    response.setLabel(field.getLabel());
                }
            }
        }
        field.setResponses(responses);
        SaverHelperDAO.update(field);
    }

    public static void deleteField(long id) {
        FieldDAO.delete(id);
    }

    public static List<Field> findAll() {
        return FieldDAO.findAll();
    }

    public static List<Field> findAllActive() {
        return FieldDAO.findAllActive();
    }
}
