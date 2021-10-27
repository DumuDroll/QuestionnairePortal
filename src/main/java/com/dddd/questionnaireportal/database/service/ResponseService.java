package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.saverDAO;
import com.dddd.questionnaireportal.database.dao.ResponseDAO;
import com.dddd.questionnaireportal.database.entity.Response;

import java.util.List;

public class ResponseService {
    public static void createResponse(Response response) {
        saverDAO.save(response);
    }

    public static void updateField(Response response) {
        ResponseDAO.update(response);
    }

    public static void deleteField(int id) {
        ResponseDAO.delete(id);
    }

    public static List<Response> findAll(){
        return ResponseDAO.findAll();
    }

    public static long findNumberOfRows(){
        return ResponseDAO.findNumberOfRows();
    }

    public static long findNumberOfColumns(){
        return ResponseDAO.findNumberOfColumns();
    }
}
