package com.dddd.questionnaireportal.beans.managedBeans;

import com.dddd.questionnaireportal.common.contants.Constants;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ApplicationScoped
@ManagedBean
public class RouterBean {
    public void goToEdit() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.EDIT_USER_URL);
    }

    public void goToPassword() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.CHANGE_PASSWORD_URL);
    }
    public void goToFields() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
    }
    public void goToResponses() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.RESPONSES_URL);
    }
    public void goToAddResponse() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.RESPONSE_ADD_URL);
    }
    public void goToLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.LOGIN_URL);
    }
}
