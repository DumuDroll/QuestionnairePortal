package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.dao.UserActivationDAO;
import com.dddd.questionnaireportal.database.entity.UserActivation;

public class UserActivationService {

    public static void update(UserActivation userActivation){
        SaverHelperDAO.update(userActivation);
    }

    public static UserActivation findByUUID(String uuid){
        return UserActivationDAO.findByUUID(uuid);
    }
}
