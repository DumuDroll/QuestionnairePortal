package com.dddd.questionnaireportal.beans.managedBeans.auth;


import com.dddd.questionnaireportal.beans.managedBeans.auth.security.userDetails.MyUserDetails;
import com.dddd.questionnaireportal.common.util.MD5Util.MD5Util;
import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.jsf.FacesContextUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@ManagedBean
@RequestScoped
public class LoginMB {

    private static final Logger logger = LogManager.getLogger();

    private String email;
    private String password;
    private boolean rememberMe;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenBasedRememberMeServices rememberMeServices;

    @PostConstruct
    public void init() {
        FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
    }

    public void logIn() {
        User user = UserService.findByEmail(getEmail());
        if (user != null) {
            if (user.isActive()) {
                if (user.getPassword().equals(MD5Util.getSecurePassword(getPassword()))) {
                    Authentication authentication;
                    UsernamePasswordAuthenticationToken authenticationToken = new
                            UsernamePasswordAuthenticationToken(getEmail(), getPassword());
                    authentication = authenticationManager.authenticate(authenticationToken);
                    if (isRememberMe()) {
                        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                        RememberMeAuthenticationToken rememberMeAuthenticationToken = new RememberMeAuthenticationToken(
                                "jsf-spring-security", userDetails,
                                userDetails.getAuthorities());
                        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                                .getExternalContext().getRequest();
                        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                                .getCurrentInstance().getExternalContext().getResponse();
                        rememberMeServices.loginSuccess(httpServletRequest, httpServletResponse,
                                rememberMeAuthenticationToken);
                    }
                    try {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } catch (Exception e) {
                        logger.catching(e);
                        SecurityContextHolder.getContext().setAuthentication(null);
                    }
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
                    } catch (IOException e) {
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
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            Constants.NO_USER_WITH_THIS_EMAIL,
                            Constants.TRY_AGAIN));
        }
    }

    public void logOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.RESPONSE_ADD_URL);
        } catch (IOException e) {
            logger.catching(e);
        }

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

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
