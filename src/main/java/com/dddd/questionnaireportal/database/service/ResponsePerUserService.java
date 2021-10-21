package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.ResponsePerUserDAO;
import com.dddd.questionnaireportal.database.entity.ResponsePerUser;

public class ResponsePerUserService {
    public static void createResponsePerUser(ResponsePerUser responsePerUser){
        ResponsePerUserDAO.save(responsePerUser);
    }

    public static void deleteField(int id){
        ResponsePerUserDAO.delete(id);
    }
}
