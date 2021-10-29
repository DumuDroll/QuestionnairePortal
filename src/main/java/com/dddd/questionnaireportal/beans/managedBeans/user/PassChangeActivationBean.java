package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.database.entity.UserActivation;
import com.dddd.questionnaireportal.database.service.UserActivationService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PassChangeActivationBean {

    @ManagedProperty(value = "#{param.key}")
    private String key;

    private boolean valid;

    @PostConstruct
    public void init() {
        UserActivation userActivation = UserActivationService.findByUUID(key);
        if (userActivation != null) {
            valid = UserActivationService.updateForPassChangeConfirmation(userActivation);
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
