package com.dddd.questionnaireportal.beans.managedBeans.auth;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.MD5Util.MD5Util;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.validation.constraints.Email;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@ManagedBean
@RequestScoped
public class SignUpMB {

    @Email(message = "must be a valid email")
    private String email;
    private String password;
    private String passwordConfirm;
    private String firstName;
    private String lastName;
    private String phoneNumber;

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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


    public String signUp() {

        if (UserService.findByEmail(getEmail()) == null) {
            if (confirmPassword()) {
                byte[] salt = MD5Util.getSalt();
                User user = new User(getEmail(), MD5Util.getSecurePassword(password, salt),
                        getFirstName(), getLastName(), false, getPhoneNumber(), salt, null);
                UserService.createUser(user);
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                Constants.PASSWORDS_DONT_MATCH,
                                Constants.TRY_AGAIN));
            }
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            Constants.EMAIL_SENT,
                            "Please confirm registration"));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Account with such an email already exists",
                            Constants.TRY_AGAIN));
        }
        return "signUp";
    }

    public boolean confirmPassword() {
        return password.equals(passwordConfirm);
    }

}
