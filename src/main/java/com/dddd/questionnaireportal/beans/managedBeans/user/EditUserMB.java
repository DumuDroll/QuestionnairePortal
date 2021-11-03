package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.beans.managedBeans.auth.security.userDetails.MyUserDetails;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

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
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        user = UserService.findByEmail(myUserDetails.getUsername());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void edit() {
        UserService.updateUser(user);
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        myUserDetails.setFirstName(user.getFirstName());
        myUserDetails.setLastName(user.getLastName());
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Your profile info has been updated", "Refresh page to see the changes"));
    }
}
