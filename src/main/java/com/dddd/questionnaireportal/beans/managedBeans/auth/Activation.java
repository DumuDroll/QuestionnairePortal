package com.dddd.questionnaireportal.beans.managedBeans.auth;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@RequestScoped
public class Activation {
    @ManagedProperty(value="#{param.key}")
    private String key;

    private boolean valid;

    @PostConstruct
    public void init() {
        // Get User based on activation key.
        // Delete activation key from database.
        // Login user.
    }

}
