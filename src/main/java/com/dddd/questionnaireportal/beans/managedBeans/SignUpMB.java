package com.dddd.questionnaireportal.beans.managedBeans;

import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.impl.UserServiceImpl;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class SignUpMB{

    @ManagedProperty("#{userService}")
    private UserServiceImpl userService;

    private User user;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String signUp() {
        User user = new User(getEmail(), getPassword(), getFirstName(), getLastName(), false, getPhoneNumber());
        if (!userService.findByEmail(user.getEmail())) {
            userService.createUser(user);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "We sent an email to your address",
                            "Please confirm registration"));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Account with such an email already exists",
                            "Please Try Again!"));
        }
        return "signUp";
    }
}
