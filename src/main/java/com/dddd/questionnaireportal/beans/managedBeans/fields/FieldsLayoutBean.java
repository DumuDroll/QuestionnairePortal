package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.FieldUiDimensions;
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
            FieldUiDimensions fieldUiDimensions = fields.get(i).getFieldUiDimensions();
            switch (fields.get(i).getType()) {
                case SINGLE_LINE_TEXT:
                    fieldUiDimensions.setUi_id(i + ":iText");
                    break;
                case MULTILINE_TEXT:
                    fieldUiDimensions.setUi_id(i + ":TextA");
                    break;
                case RADIO_BUTTON:
                    fieldUiDimensions.setUi_id(i + ":Radio");
                    break;
                case CHECKBOX:
                    fieldUiDimensions.setUi_id(i + ":check");
                    break;
                case COMBOBOX:
                    fieldUiDimensions.setUi_id(i + ":combo");
                    break;
                case DATE:
                    fieldUiDimensions.setUi_id(i + ":cDate");
                    break;
            }
        }
    }

    public void save() {
        String json = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");
        Gson gson = new Gson();
        List<ItemDTO> items = gson.fromJson(json, new TypeToken<List<ItemDTO>>() {
        }.getType());

        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).getFieldUiDimensions().setHeight(items.get(i).getHeight());
            fields.get(i).getFieldUiDimensions().setWidth(items.get(i).getWidth());
            fields.get(i).getFieldUiDimensions().setPositionTopForCollision(items.get(i).getPositionTopForCollision());
            fields.get(i).getFieldUiDimensions().setPositionLeftForCollision(items.get(i).getPositionLeftForCollision());
        }

        if (loopForOverlap(fields)) {
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
            if (field.getFieldUiDimensions().getUi_id().equals(s)) {
                field.getFieldUiDimensions().setPositionLeft(left);
                field.getFieldUiDimensions().setPositionTop(top);
            }
        }
    }

    public void setDefault() {
        for (Field field : fields) {
            field.getFieldUiDimensions().setPositionTop(null);
            field.getFieldUiDimensions().setPositionLeft(null);
        }
    }

    public boolean loopForOverlap(List<Field> fields) {
        for (int i = 0; i < fields.size() - 1; i++) {
            for (int j = i + 1; j < fields.size(); j++) {
                if (doOverlap(fields.get(i), fields.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean doOverlap(Field field1, Field field2) {
        Rectangle rectangle1 = getCoords(field1);
        Rectangle rectangle2 = getCoords(field2);
        return rectangle1.intersects(rectangle2);
    }

    private static Rectangle getCoords(Field field) {
        Rectangle rectangle = new Rectangle();
        rectangle.height = (int) Double.parseDouble(field.getFieldUiDimensions().getHeight());
        rectangle.width = (int) Double.parseDouble(field.getFieldUiDimensions().getWidth());
        rectangle.x = (int) Double.parseDouble(field.getFieldUiDimensions().getPositionLeftForCollision());
        rectangle.y = (int) Double.parseDouble(field.getFieldUiDimensions().getPositionTopForCollision());
        return rectangle;
    }

    static class ItemDTO{

        private String height;
        private String width;
        private String positionTopForCollision;
        private String positionLeftForCollision;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getPositionTopForCollision() {
            return positionTopForCollision;
        }

        public void setPositionTopForCollision(String positionTopForCollision) {
            this.positionTopForCollision = positionTopForCollision;
        }

        public String getPositionLeftForCollision() {
            return positionLeftForCollision;
        }

        public void setPositionLeftForCollision(String positionLeftForCollision) {
            this.positionLeftForCollision = positionLeftForCollision;
        }
    }
}
