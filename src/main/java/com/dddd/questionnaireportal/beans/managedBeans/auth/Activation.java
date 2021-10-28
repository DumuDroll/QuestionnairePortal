package com.dddd.questionnaireportal.beans.managedBeans.auth;

import com.dddd.questionnaireportal.beans.managedBeans.LoginMB;
import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.SessionUtil.SessionUtil;
import com.dddd.questionnaireportal.database.entity.UserActivation;
import com.dddd.questionnaireportal.database.service.UserActivationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@ManagedBean
@RequestScoped
public class Activation {

    @ManagedProperty(value = "#{param.key}")
    private String key;

    private boolean valid;

    @PostConstruct
    public void init() {

        UserActivation userActivation = UserActivationService.findByUUID(UUID.fromString(key));
        if (userActivation.getConfirmationDate() != null) {
            login(userActivation);
            valid = true;
        } else {
            Date date = new Date();
            if (userActivation.getConfirmationExpireDate().compareTo(date) <= 0) {
                userActivation.setConfirmationDate(date);
                valid = true;
                userActivation.getUser().setActive(true);
                UserActivationService.update(userActivation);
                login(userActivation);
            }
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void login(UserActivation userActivation) {
        HttpSession session = SessionUtil.getSession();
        session.setAttribute("email", userActivation.getUser().getEmail());
        session.setAttribute("firstName", userActivation.getUser().getFirstName());
        session.setAttribute("lastName", userActivation.getUser().getLastName());
    }

}
