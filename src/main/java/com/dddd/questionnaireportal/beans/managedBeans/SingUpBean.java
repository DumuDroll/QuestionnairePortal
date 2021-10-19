package com.dddd.questionnaireportal.beans.managedBeans;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@URLMapping(id="sign-up", pattern = "/sign-up", viewId = "sign-up.xhtml")
public class SingUpBean {
}
