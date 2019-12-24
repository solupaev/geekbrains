package com.geekbrains.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;

public class FilterTaskWidget extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox nameText;

    @UiField
    TextBox ownerText;

    @UiField
    TextBox executerText;

    @UiField
    ListBox statusBox;

    private TasksTableWidget tasksTableWidget;

    @UiTemplate("FilterTaskForm.ui.xml")
    interface FilterTaskFormBinder extends UiBinder<Widget, FilterTaskWidget> {
    }

    private static FilterTaskWidget.FilterTaskFormBinder uiBinder = GWT.create(FilterTaskWidget.FilterTaskFormBinder.class);

    public FilterTaskWidget(TasksTableWidget tasksTableWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.form.setAction(Defaults.getServiceRoot().concat("tasks"));
        this.tasksTableWidget = tasksTableWidget;
    }

    @UiHandler("form")
    public void onSubmit(FormPanel.SubmitEvent event) {
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {

    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        tasksTableWidget.refresh(nameText.getText(), ownerText.getText(), executerText.getText(), statusBox.getSelectedValue());
    }
}
