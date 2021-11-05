package com.dddd.questionnaireportal.beans.managedBeans.fields;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.enums.Type;
import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.entity.Field;
import com.dddd.questionnaireportal.database.entity.Response;
import com.dddd.questionnaireportal.database.service.FieldService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.dnd.Draggable;
import org.primefaces.component.graphicimage.GraphicImage;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.DragDropEvent;
import org.w3c.dom.events.Event;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class FieldsLayoutBean {

    private static final Logger logger = LogManager.getLogger();

    private List<Field> fields;
    private String position;

    private Panel myPanel;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @PostConstruct
    public void init() {
        fields = FieldService.findAll();
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

    public void save() {
        for (Field field : fields) {
            SaverHelperDAO.update(field);
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(Constants.FIELDS_URL);
        } catch (IOException e) {
            logger.catching(e);
        }
    }

    public void onDrop(DragDropEvent<Event> dragDropEvent) {
        String dragId = dragDropEvent.getDragId();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String left = params.get(dragId + "_left");
        String top = params.get(dragId + "_top");
        String s = dragId.substring(dragId.length()-7);
        for (Field field : fields) {
            if (field.getUi_id().equals(s)) {
                field.setPositionLeft(left);
                field.setPositionTop(top);
            }
        }
    }

    public void setDefault(){
        for (Field field: fields){
            field.setPositionTop(null);
            field.setPositionLeft(null);
        }
    }
}
