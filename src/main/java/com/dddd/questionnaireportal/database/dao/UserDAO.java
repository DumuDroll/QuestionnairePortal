package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.User;

import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
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