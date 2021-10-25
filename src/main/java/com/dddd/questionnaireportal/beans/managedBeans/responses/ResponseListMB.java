package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.entity.ResponsePerUser;
import com.dddd.questionnaireportal.database.service.ResponsePerUserService;
import org.primefaces.component.datatable.DataTable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class ResponseListMB {

    private List<ResponsePerUser> responsePerUserList;
    private List<Response> headers;
    private List<String> columns = new ArrayList<>();
    List<Map<String, Object>> rows = new ArrayList<>();

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
        populateColumns(columns, headers.size());
        populateRows(rows, headers.size(), responsePerUserList.size());
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

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }

    public void populateRows(List<Map<String,Object>> rows, int columnNumber, int rowNumber){
        for(int i = 0 ; i < rowNumber ; i++)
        {
            List<Response> responses = responsePerUserList.get(i).getResponses();
            Map<String,Object> m = new HashMap<>();
            for(int j = 0 ; j < columnNumber; j++)
            {
                m.put("Column" + j, responses.get(j).getResponse());
            }
            rows.add(m);
        }
    }

    private void populateColumns(List<String> list, int size) {
        for(int i = 0 ; i < size ; i++)
            list.add("Column" + i);
    }
}
