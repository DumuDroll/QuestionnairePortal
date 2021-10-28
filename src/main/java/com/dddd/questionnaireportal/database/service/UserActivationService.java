package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.dao.UserActivationDAO;
import com.dddd.questionnaireportal.database.entity.UserActivation;

import java.util.UUID;

public class UserActivationService {

    public static void save(UserActivation userActivation){
        SaverHelperDAO.save(userActivation);
    }

    public static void update(UserActivation userActivation){
        SaverHelperDAO.update(userActivation);
    }

    public static UserActivation findByUUID(UUID uuid){
        return UserActivationDAO.findByUUID(uuid);
    }
}
