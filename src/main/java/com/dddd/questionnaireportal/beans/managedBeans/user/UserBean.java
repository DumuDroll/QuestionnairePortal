package com.dddd.questionnaireportal.beans.managedBeans.user;

import com.dddd.questionnaireportal.beans.managedBeans.auth.security.userDetails.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class UserBean {

    public String getFullName() {
        String fullName;
        try {

            MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            fullName = myUserDetails.getFirstName() + " " +
                    myUserDetails.getLastName();
        } catch (NullPointerException npe) {
            return null;
        }
        return fullName;
    }

}
