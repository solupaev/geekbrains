package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.TaskDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class EditTaskWidget extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox nameText;

    @UiField
    TextBox ownerText;

    @UiField
    TextBox executerText;

    @UiField
    TextArea summaryText;

    @UiField
    ListBox statusBox;

    private TaskDto task;
    private DialogBox dialog;
    private TasksTableWidget tasksTableWidget;

    @UiTemplate("EditTaskForm.ui.xml")
    interface EditTaskBinder extends UiBinder<Widget, EditTaskWidget> {
    }

    private static EditTaskWidget.EditTaskBinder uiBinder = GWT.create(EditTaskWidget.EditTaskBinder.class);

    public EditTaskWidget(TaskDto task, DialogBox dialog, TasksTableWidget tasksTableWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.nameText.setText(task.getName());
        this.ownerText.setText(task.getOwner());
        this.executerText.setText(task.getExecuter());
        this.summaryText.setText(task.getSummary());
        for (int i = 0; i < statusBox.getItemCount(); i++) {
            if (statusBox.getValue(i).equals(task.getStatus())) {
                statusBox.setSelectedIndex(i);
                break;
            }
        }
        this.task = task;
        this.dialog = dialog;
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
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        TasksClient client = GWT.create(TasksClient.class);
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");

        client.addTask(new TaskDto(task.getId(),nameText.getText(),ownerText.getText(),executerText.getText(),summaryText.getText(),statusBox.getSelectedValue()), token, new MethodCallback<TaskDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, TaskDto result) {
                dialog.hide();
                tasksTableWidget.refresh("","","","");
            }
        });
    }

    @UiHandler("btnClose")
    public void closeClick(ClickEvent event) {
        this.dialog.hide();
    }
}
