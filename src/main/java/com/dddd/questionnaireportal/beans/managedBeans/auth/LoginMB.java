package com.dddd.questionnaireportal.beans.managedBeans.auth;


import com.dddd.questionnaireportal.common.util.MD5Util.MD5Util;
import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.jsf.FacesContextUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class LoginMB {

    private static final Logger logger = LogManager.getLogger();

    private String email;
    private String password;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init(){
        FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void logIn() {
        User user = UserService.findByEmail(email);
        if (user!=null) {
            if (user.isActive()) {
                if (user.getPassword().equals(MD5Util.getSecurePassword(password))) {
                    try {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( email, password );
                        Authentication authentication = authenticationManager.authenticate(authenticationToken);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } catch (Exception e) {
                        logger.catching(e);
                        SecurityContextHolder.getContext().setAuthentication(null);
                    }
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
                    } catch (IOException e){
                        logger.catching(e);
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    Constants.INVALID_PASSWORD,
                                    Constants.TRY_AGAIN));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Account Is Not Activated!",
                                "Please Confirm Registration!"));
            }
        }else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            Constants.NO_USER_WITH_THIS_EMAIL,
                            Constants.TRY_AGAIN));
        }
    }

    public void logOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.RESPONSE_ADD_URL);
        }catch (IOException e){
            logger.catching(e);
        }

    }

}
