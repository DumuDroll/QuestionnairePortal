package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.entity.ResponsePerUser;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.ResponsePerUserService;
import org.primefaces.component.column.Column;
import org.primefaces.component.columngroup.ColumnGroup;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.row.Row;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ResponseListMB {

    private List<ResponsePerUser> responsePerUserList;
    private List<Response> headers;

    private DataTable myDataTable;

    public DataTable getMyDataTable() {
        return myDataTable;
    }

    public void setMyDataTable(DataTable myDataTable) {
        this.myDataTable = myDataTable;
    }

    @PostConstruct
    public void init() {
        responsePerUserList = ResponsePerUserService.findAll();
        headers=responsePerUserList.get(0).getResponses();
    }

    public List<ResponsePerUser> getResponsePerUserList() {
        return responsePerUserList;
    }

    public void setResponsePerUserList(List<ResponsePerUser> responsePerUserList) {
        this.responsePerUserList = responsePerUserList;
    }

    public List<Response> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Response> headers) {
        this.headers = headers;
    }
}
