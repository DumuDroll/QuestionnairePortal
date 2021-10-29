package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.MD5Util.MD5Util;
import com.dddd.questionnaireportal.common.util.SessionUtil.SessionUtil;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserActivationService;
import com.dddd.questionnaireportal.database.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

@ManagedBean
@ViewScoped
public class ChangePasswordMB {

    private String password;

    private String newPassword;

    private String newPassConfirm;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassConfirm() {
        return newPassConfirm;
    }

    public void setNewPassConfirm(String newPassConfirm) {
        this.newPassConfirm = newPassConfirm;
    }

    public void change() throws MessagingException {
        User user = UserService.findByEmail(SessionUtil.getSession().getAttribute("email").toString());
        if (user.getPassword().equals(MD5Util.getSecurePassword(password, user.getSalt()))) {
            if (newPassword.equals(newPassConfirm)) {
                UserActivationService.updateForPassChange(user, newPassword);
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "We've sent an email",
                                "Check your mailbox to confirm password change!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                Constants.PASSWORDS_DONT_MATCH,
                                Constants.TRY_AGAIN));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            Constants.INVALID_PASSWORD,
                            Constants.TRY_AGAIN));
        }
    }
}