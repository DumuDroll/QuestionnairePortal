package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.entity.UserActivation;
import com.dddd.questionnaireportal.database.service.UserActivationService;
import com.dddd.questionnaireportal.database.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Date;

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
            Date date = new Date();
            if (date.compareTo(userActivation.getPassChangeExpireDate()) <= 0) {
                userActivation.setPassChangeExpireDate(date);
                valid = true;
                User user = userActivation.getUser();
                user.setPassword(userActivation.getNewPass());
                UserService.updateUser(user);
                UserActivationService.update(userActivation);
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
