package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.entity.ResponsePerUser;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.ResponsePerUserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ResponseListMB {

    private List<ResponsePerUser> responsePerUserList;

    @PostConstruct
    public void init() {
        responsePerUserList = ResponsePerUserService.findAll();
    }

    public List<ResponsePerUser> getResponsePerUserList() {
        return responsePerUserList;
    }

    public void setResponsePerUserList(List<ResponsePerUser> responsePerUserList) {
        this.responsePerUserList = responsePerUserList;
    }

}
