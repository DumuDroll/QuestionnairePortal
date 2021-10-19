package com.dddd.questionnaireportal.beans.managedBeans;


import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.impl.UserServiceImpl;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
@Named
@SessionScoped
@URLMapping(id="login", pattern = "/", viewId = "login.xhtml")
public class LoginBean {


    private final @Named("UserService") UserServiceImpl userService;

    private String uname;
    private String password;
    private User user;

    @Inject
    public LoginBean(UserServiceImpl userService) {
        this.userService = userService;
    }

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
            return "home";
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
