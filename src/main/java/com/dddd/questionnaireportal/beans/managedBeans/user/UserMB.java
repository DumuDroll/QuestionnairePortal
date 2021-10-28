package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.common.util.SessionUtil.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class UserMB {

    public String getFullName() {
        String fullName;
        try{
            fullName = SessionUtil.getSession().getAttribute("firstName").toString() + " " +
                   SessionUtil.getSession().getAttribute("lastName").toString();
       } catch (NullPointerException npe){
           return null;
       }
        return fullName;
    }

}
