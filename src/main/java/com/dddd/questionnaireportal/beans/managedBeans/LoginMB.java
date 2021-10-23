package com.dddd.questionnaireportal.beans.managedBeans;


import com.dddd.questionnaireportal.common.MD5Util.MD5Util;
import com.dddd.questionnaireportal.common.SessionUtil.SessionUtil;
import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class LoginMB {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void logIn() throws IOException{
        User user = UserService.findByEmail(email);
        if (user!=null) {
            if (user.isActive()) {
                if (user.getPassword().equals(MD5Util.getSecurePassword(password, user.getSalt()))) {
                    HttpSession session = SessionUtil.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("firstName", user.getFirstName());
                    session.setAttribute("lastName", user.getLastName());
                    FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Invalid Password!",
                                    "Please Try Again!"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Account Is Not Activated!",
                                "Please Confirm Registration!"));
            }
        }else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login",
                            "Please Try Again!"));
        }
    }

    public void logOut() throws IOException {
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.RESPONSE_ADD_URL);
    }

    public boolean isLoggedIn() {
        return SessionUtil.getSession().getAttribute("email") != null;
    }
}
