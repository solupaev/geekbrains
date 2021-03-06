package com.geekbrains.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Defaults;

public class AddTaskFormWidget extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox nameText;

    @UiField
    TextBox ownerText;

    @UiField
    TextBox executerText;

    @UiField
    TextBox summaryText;

    private TasksTableWidget tasksTableWidget;

    @UiTemplate("AddTaskForm.ui.xml")
    interface AddTaskFormBinder extends UiBinder<Widget, AddTaskFormWidget> {
    }

    private static AddTaskFormWidget.AddTaskFormBinder uiBinder = GWT.create(AddTaskFormWidget.AddTaskFormBinder.class);

    public AddTaskFormWidget(TasksTableWidget tasksTableWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.form.setAction(Defaults.getServiceRoot().concat("tasks"));
        this.tasksTableWidget = tasksTableWidget;
    }

    @UiHandler("form")
    public void onSubmit(FormPanel.SubmitEvent event) {
        if (nameText.getText().length() < 4) {
            Window.alert("Название задачи должно быть не менее 4 символов");
            event.cancel();
        }
        if (ownerText.getText().length() == 0) {
            Window.alert("Не заполнен автор");
            event.cancel();
        }
        if (executerText.getText().length() == 0) {
            Window.alert("Не заполнен исполнитель");
            event.cancel();
        }
        if (summaryText.getText().length() == 0) {
            Window.alert("Не заполнено описание");
            event.cancel();
        }
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        tasksTableWidget.refresh();
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        form.submit();
    }
}
