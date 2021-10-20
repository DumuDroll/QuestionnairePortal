package com.dddd.questionnaireportal.beans.managedBeans;


import com.dddd.questionnaireportal.common.SessionUtil.SessionUtil;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.impl.UserServiceImpl;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class LoginMB {

    @ManagedProperty("#{userService}")
    private UserServiceImpl userService;

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    private String uname;
    private String password;

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
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("username", uname);
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
    public String logOut(){
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        return "login";
    }

    public boolean isLoggedIn(){
        return SessionUtil.getSession().getAttribute("username") != null;
    }
}
