package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.SessionUtil.SessionUtil;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class EditUserMB {
    private User user;

    @PostConstruct
    private void init(){
        user = UserService.findByEmail(SessionUtil.getSession().getAttribute(Constants.EMAIL).toString());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void edit() {
        UserService.updateUser(user);
        SessionUtil.getSession().setAttribute(Constants.FIRST_NAME, user.getFirstName());
        SessionUtil.getSession().setAttribute(Constants.LAST_NAME, user.getLastName());
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Your profile info has been updated", ""));
    }
}
