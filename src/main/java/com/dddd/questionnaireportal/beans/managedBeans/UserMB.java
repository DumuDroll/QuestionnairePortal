package com.dddd.questionnaireportal.beans.managedBeans;

import com.dddd.questionnaireportal.common.SessionUtil.SessionUtil;
import com.dddd.questionnaireportal.common.contants.Constants;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@SessionScoped
@ManagedBean
public class UserMB {

    public String getFullName(){
        return SessionUtil.getSession().getAttribute("firstName").toString()+
                SessionUtil.getSession().getAttribute("lastName").toString();
    }
    public void goToEdit() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.EDIT_USER_URL);
    }

    public void goToPassword() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.CHANGE_PASSWORD_URL);
    }
}
