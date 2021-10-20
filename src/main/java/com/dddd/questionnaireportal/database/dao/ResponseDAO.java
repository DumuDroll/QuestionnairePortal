package com.dddd.questionnaireportal.database.dao;


import com.dddd.questionnaireportal.database.entity.Response;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ResponseDAO extends GenericDAO<Response>{
    public ResponseDAO(){
        super(Response.class);
    }
}
