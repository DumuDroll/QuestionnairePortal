package com.dddd.questionnaireportal.database.dao;


import com.dddd.questionnaireportal.common.Constants;
import com.dddd.questionnaireportal.database.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserDAO extends GenericDAO<User> {


    public UserDAO() {
        super(User.class);
    }

    public User findUserByEmail(String email){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        return super.findOneResult(Constants.FIND_BY_EMAIL, parameters);
    }


}
