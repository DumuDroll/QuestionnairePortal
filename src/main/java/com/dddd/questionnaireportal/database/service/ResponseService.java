package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.ResponseDAO;
import com.dddd.questionnaireportal.database.entity.Response;

public class ResponseService {
    public static void createResponse(Response response) {
        ResponseDAO.save(response);
    }

    public static void updateField(Response response) {
        ResponseDAO.update(response);
    }

    public static void deleteField(int id) {
        ResponseDAO.delete(id);
    }

}
