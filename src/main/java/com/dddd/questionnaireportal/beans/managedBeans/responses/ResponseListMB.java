package com.dddd.questionnaireportal.beans.managedBeans.responses;

import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.dddd.questionnaireportal.database.service.ResponseService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean
@ViewScoped
public class ResponseListMB {

    private List<Response> responses;
    private List<String> columns = new ArrayList<>();
    private List<Map<String, Object>> rows = new ArrayList<>();

    @PostConstruct
    public void init() {
        responses = ResponseService.findAll();
        populateRows();
        List<Field> fields = FieldService.findAllActive();
        fields.forEach(field -> columns.add(field.getLabel()));
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

    public void addRow(){
        Map<String, Object> map1 = new Gson()
                .fromJson(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param1"),
                        new TypeToken<HashMap<String, Object>>() {
                }.getType());
       rows.add(map1);
    }

}
