package com.dddd.questionnaireportal.database.dao;


import com.dddd.questionnaireportal.database.entity.ResponsePerUser;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ResponsePerUserDAO extends GenericDAO<ResponsePerUser> {
    public ResponsePerUserDAO(){
        super(ResponsePerUser.class);
    }
}
