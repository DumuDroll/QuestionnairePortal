package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.beans.managedBeans.auth.security.userDetails.MyUserDetails;
import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@ViewScoped
public class NewPasswordBean {

    private static final Logger logger = LogManager.getLogger();

    private String newPassword;

    private String newPassConfirm;

    public void change() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = UserService.findByEmail(myUserDetails.getUsername());
        if (getNewPassword().equals(getNewPassConfirm())) {
            UserService.updateUserForPassReset(user, getNewPassword());
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
            } catch (IOException e) {
                logger.catching(e);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            Constants.PASSWORDS_DONT_MATCH,
                            Constants.TRY_AGAIN));
        }
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
}
