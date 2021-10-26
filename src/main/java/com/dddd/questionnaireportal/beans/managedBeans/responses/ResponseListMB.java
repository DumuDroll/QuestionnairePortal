package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.ResponseService;
import org.primefaces.component.datatable.DataTable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.*;

@ManagedBean
@ViewScoped
public class ResponseListMB {

    private List<Response> headers;
    private List<Response> responses;
    private List<String> columns = new ArrayList<>();
    private List<Map<String, Object>> rows = new ArrayList<>();
    private long columnsNumber;

    @PostConstruct
    public void init() {
        responses = ResponseService.findAll();
        populateRows();
        List<Field> fields = FieldService.findAllActive();
        fields.forEach(field -> columns.add(field.getLabel()));
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
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

    public long getColumnsNumber() {
        return columnsNumber;
    }

    public void setColumnsNumber(long columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

    public void populateRows() {
        Map<String, Object> tempResponses = new HashMap<>();
        StringBuilder currentUuid = new StringBuilder();
        Iterator<Response> iterator = responses.iterator();
        while (iterator.hasNext()) {
            Response response = iterator.next();
            if (!currentUuid.toString().equals(response.getResponsePerUser().toString())) {
                if (!currentUuid.toString().equals("")) {
                    rows.add(tempResponses);
                    currentUuid.setLength(0);
                }
                currentUuid.append(response.getResponsePerUser().toString());
                tempResponses = new HashMap<>();
            }
            if(response.getResponse()==null){
                tempResponses.put(response.getLabel(), "N/A");
            }else{
                tempResponses.put(response.getLabel(), response.getResponse());
            }
            if (!iterator.hasNext()) {
                rows.add(tempResponses);
            }
        }

    }

}
