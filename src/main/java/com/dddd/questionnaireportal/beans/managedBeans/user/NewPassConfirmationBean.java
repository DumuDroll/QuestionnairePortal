package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.database.entity.UserActivation;
import com.dddd.questionnaireportal.database.service.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.jsf.FacesContextUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@ManagedBean
@RequestScoped
public class NewPassConfirmationBean {

    @Autowired
    private UserDetailsService userDetailsService;

    @ManagedProperty(value = "#{param.key}")
    private String key;

    private boolean valid;

    @PostConstruct
    public void init() {
        UserActivation userActivation = UserActivationService.findByUUID(key);
        if (userActivation != null) {
            valid = UserActivationService.updateForNewPassConfirmation(userActivation);
            if(valid){
                FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                        .getAutowireCapableBeanFactory().autowireBean(this);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userActivation.getUser().getEmail());
                UsernamePasswordAuthenticationToken authenticationToken = new
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
