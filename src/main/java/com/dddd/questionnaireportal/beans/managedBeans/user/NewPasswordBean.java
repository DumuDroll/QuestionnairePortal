package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.SessionUtil.SessionUtil;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public void change() {
        User user = UserService.findByEmail(SessionUtil.getSession().getAttribute(Constants.EMAIL).toString());
        if (newPassword.equals(newPassConfirm)) {
            user.setPassword(newPassword);
            UserService.updateUserForPassReset(user, newPassword);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../fields/fieldList.xhtml");
            }catch (IOException e){
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
}
