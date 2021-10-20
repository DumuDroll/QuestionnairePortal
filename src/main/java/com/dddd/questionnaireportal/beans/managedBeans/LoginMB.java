package com.dddd.questionnaireportal.beans.managedBeans;


import com.dddd.questionnaireportal.common.SessionUtil.SessionUtil;
import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.impl.UserServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class LoginMB {

    private final UserServiceImpl userService = new UserServiceImpl();

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

    public void logIn() throws IOException {
        boolean result = userService.isValidLogin(uname, password);
        User user = userService.findByEmail(uname);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (result) {
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("email", uname);
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
            facesContext.getExternalContext().redirect(Constants.FIELDS_URL);
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));
        }
    }
    public void logOut() throws IOException {
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.LOGIN_URL);
    }

    public boolean isLoggedIn(){
        return SessionUtil.getSession().getAttribute("email") != null;
    }
}
