package com.dddd.questionnaireportal.beans.managedBeans;


import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.impl.UserServiceImpl;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginMB {


    private final  UserServiceImpl userService = new UserServiceImpl();

    private String uname;
    private String password;
    private User user;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String logIn() {
        boolean result = userService.isValidLogin(uname, password);
        if (result) {
            return "fields";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));
            return "login";
        }
    }
    public boolean isLoggedIn(){
        return user != null;
    }
}
