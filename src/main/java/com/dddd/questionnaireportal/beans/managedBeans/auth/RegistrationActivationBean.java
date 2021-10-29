package com.dddd.questionnaireportal.beans.managedBeans.auth;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.entity.UserActivation;
import com.dddd.questionnaireportal.database.service.UserActivationService;
import com.dddd.questionnaireportal.database.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.util.Date;

@ManagedBean
@RequestScoped
public class RegistrationActivationBean {

    @ManagedProperty(value = "#{param.key}")
    private String key;
    private String email;
    private boolean valid;

    @PostConstruct
    public void init() {
        UserActivation userActivation = UserActivationService.findByUUID(key);
        if (userActivation != null) {
            if (userActivation.getUser().isActive()) {
                valid = true;
            } else {
                Date date = new Date();
                if (date.compareTo(userActivation.getConfirmationExpireDate()) <= 0) {
                    valid = true;
                    User user = userActivation.getUser();
                    user.setActive(true);
                    UserService.updateUser(user);
                    UserActivationService.update(userActivation);
                }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String send() {
        User user = UserService.findByEmail(email);
        if (user != null) {
            UserService.updateActivationLink(user);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Check your email for new confirmation letter",
                            null));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            Constants.NO_USER_WITH_THIS_EMAIL,
                            null));
        }
        return "reActivation";
    }

}
