package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.service.FieldService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.DragDropEvent;
import org.w3c.dom.events.Event;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class FieldsLayoutBean {

    private static final Logger logger = LogManager.getLogger();

    private List<Field> fields;

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @PostConstruct
    public void init() {
        fields = FieldService.findAllActive();
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            switch (field.getType()) {
                case SINGLE_LINE_TEXT:
                    field.setUi_id(i + ":iText");
                    break;
                case MULTILINE_TEXT:
                    field.setUi_id(i + ":TextA");
                    break;
                case RADIO_BUTTON:
                    fields.get(i).setUi_id(i + ":Radio");
                    break;
                case CHECKBOX:
                    fields.get(i).setUi_id(i + ":check");
                    break;
                case COMBOBOX:
                    fields.get(i).setUi_id(i + ":combo");
                    break;
                case DATE:
                    fields.get(i).setUi_id(i + ":cDate");
                    break;
            }
        }
    }

    static class ItemDTO {
        private String width;
        private String height;
        private String positionLeft;
        private String positionTop;

    }

    public void save() {
        String json = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");
        Gson gson = new Gson();
        List<ItemDTO> items = gson.fromJson(json, new TypeToken<List<ItemDTO>>() {
        }.getType());

        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).setHeight(items.get(i).height);
            fields.get(i).setWidth(items.get(i).width);
            fields.get(i).setPositionTopForCollision(items.get(i).positionTop);
            fields.get(i).setPositionLeftForCollision(items.get(i).positionLeft);
        }
        boolean overlap = false;
        for (int i = 0; i < fields.size() - 1; i++) {
            for (int j = i + 1; j < fields.size(); j++) {
                if (doOverlap(fields.get(i), fields.get(j))) {
                    overlap = true;
                    break;
                }
            }
        }
        if (overlap) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Some fields are overlapped!",
                            "Move them and try again"));
        } else {
            for (Field field : fields) {

                SaverHelperDAO.update(field);
            }
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
            } catch (IOException e) {
                logger.catching(e);
            }
        }

    }

    public void onDrop(DragDropEvent<Event> dragDropEvent) {
        String dragId = dragDropEvent.getDragId();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String left = params.get(dragId + "_left");
        String top = params.get(dragId + "_top");
        String s = dragId.substring(dragId.length() - 7);
        for (Field field : fields) {
            if (field.getUi_id().equals(s)) {
                field.setPositionLeft(left);
                field.setPositionTop(top);
            }
        }
    }

    public void setDefault() {
        for (Field field : fields) {
            field.setPositionTop(null);
            field.setPositionLeft(null);
        }
    }

    static boolean doOverlap(Field field1, Field field2) {

        Rectangle rectangle1 = new Rectangle();
        rectangle1.height = (int) Double.parseDouble(field1.getHeight());
        rectangle1.width = (int) Double.parseDouble(field1.getWidth());
        rectangle1.x = (int) Double.parseDouble(field1.getPositionLeftForCollision());
        rectangle1.y = (int) Double.parseDouble(field1.getPositionTopForCollision());

        Rectangle rectangle2 = new Rectangle();
        rectangle2.height = (int) Double.parseDouble(field2.getHeight());
        rectangle2.width = (int) Double.parseDouble(field2.getWidth());
        rectangle2.x = (int) Double.parseDouble(field2.getPositionLeftForCollision());
        rectangle2.y = (int) Double.parseDouble(field2.getPositionTopForCollision());

//        double x1 = Double.parseDouble(field1.getPositionLeftForCollision());
//        double y1 = Double.parseDouble(field1.getPositionTopForCollision()) - Double.parseDouble(field1.getHeight());
//        double x2 = Double.parseDouble(field1.getPositionLeftForCollision()) + Double.parseDouble(field1.getWidth());
//        double y2 = Double.parseDouble(field1.getPositionTopForCollision());
//
//
//        double x3 = Double.parseDouble(field2.getPositionLeftForCollision());
//        double y3 = Double.parseDouble(field2.getPositionTopForCollision()) - Double.parseDouble(field2.getHeight());
//        double x4 = Double.parseDouble(field2.getPositionLeftForCollision()) + Double.parseDouble(field2.getWidth());
//        double y4 = Double.parseDouble(field2.getPositionTopForCollision());

//        if (l1.x == r1.x || l1.y == r1.y || l2.x == r2.x || l2.y == r2.y)
//        {
//            return false;
//        }
//
//
//        if (l1.x >= r2.x || l2.x >= r1.x || r1.y >= l2.y || r2.y >= l1.y) {
//            return false;
//        }
//        (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
        return rectangle1.intersects(rectangle2);
    }
}
