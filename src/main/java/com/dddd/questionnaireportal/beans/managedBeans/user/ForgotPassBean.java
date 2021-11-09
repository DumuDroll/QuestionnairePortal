package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserActivationService;
import com.dddd.questionnaireportal.database.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ForgotPassBean {

    private String email;

    public void sendResetPassLetter() {
        User user = UserService.findByEmail(getEmail());
        if (user != null) {
            UserActivationService.updateForForgotPassLetter(user);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            Constants.EMAIL_SENT,
                            null));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            Constants.NO_USER_WITH_THIS_EMAIL,
                            Constants.TRY_AGAIN));
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
