package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.saverDAO;
import com.dddd.questionnaireportal.database.dao.ResponseDAO;
import com.dddd.questionnaireportal.database.entity.Response;

import java.util.List;

public class ResponseService {
    public static void createResponse(Response response) {
        saverDAO.save(response);
    }

    public static List<Response> findAll(){
        return ResponseDAO.findAll();
    }
}
