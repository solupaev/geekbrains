package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.TaskDto;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TasksTableWidget extends Composite {
    @UiField
    CellTable<TaskDto> table;

    private TasksClient client;

    @UiTemplate("TasksTable.ui.xml")
    interface TasksTableBinder extends UiBinder<Widget, TasksTableWidget> {
    }

    private static TasksTableBinder uiBinder = GWT.create(TasksTableBinder.class);

    public TasksTableWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        TextColumn<TaskDto> idColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getId().toString();
            }
        };
        table.addColumn(idColumn, "ID");

        TextColumn<TaskDto> nameColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getName();
            }
        };
        table.addColumn(nameColumn, "Тема");

        TextColumn<TaskDto> ownerColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getOwner();
            }
        };
        table.addColumn(ownerColumn, "Автор");

        TextColumn<TaskDto> executerColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getExecuter();
            }
        };
        table.addColumn(executerColumn, "Исполнитель");

        TextColumn<TaskDto> statusColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                switch (taskDto.getStatus()) {
                    case ("OPEN"):
                        return "Открыта";
                    case ("PLANED"):
                        return "Запланирована";
                    case ("INPROGRESS"):
                        return "В работе";
                    case ("CLOSED"):
                        return "Закрыта";
                    default:
                        return "";
                }
            }
        };
        table.addColumn(statusColumn, "Статус");

        String token = Storage.getLocalStorageIfSupported().getItem("jwt");

        client = GWT.create(TasksClient.class);

        Column<TaskDto, TaskDto> actionColumn = new Column<TaskDto, TaskDto>(
                new ActionCell<TaskDto>("REMOVE", new ActionCell.Delegate<TaskDto>() {
                    @Override
                    public void execute(TaskDto task) {
                        client.removeTask(token, task.getId().toString(), new MethodCallback<Void>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {
                                GWT.log(throwable.toString());
                                GWT.log(throwable.getMessage());
                            }

                            @Override
                            public void onSuccess(Method method, Void result) {
                                refresh("","","","");
                            }
                        });
                    }
                })) {
            @Override
            public TaskDto getValue(TaskDto task) {
                return task;
            }
        };
        table.addColumn(actionColumn, "Actions");

        Column<TaskDto, TaskDto> editColumn = new Column<TaskDto, TaskDto>(
                new ActionCell<TaskDto>("EDIT/DETAIL", new ActionCell.Delegate<TaskDto>() {
                    @Override
                    public void execute(TaskDto task) {
                        showEditDialog(task);
                    }
                })) {
            @Override
            public TaskDto getValue(TaskDto task) {
                return task;
            }
        };
        table.addColumn(editColumn, "");

        table.setColumnWidth(idColumn, 50, Style.Unit.PX);
        table.setColumnWidth(nameColumn, 100, Style.Unit.PX);
        table.setColumnWidth(ownerColumn, 100, Style.Unit.PX);
        table.setColumnWidth(executerColumn, 100, Style.Unit.PX);
        table.setColumnWidth(statusColumn, 100, Style.Unit.PX);
        table.setColumnWidth(actionColumn, 100, Style.Unit.PX);
        table.setColumnWidth(editColumn, 100, Style.Unit.PX);
    }

    private DialogBox showEditDialog(TaskDto task) {
        final DialogBox dialog = new DialogBox(false, true);
        dialog.setText("Просмотр/редактирование задачи(id=" + task.getId() + ")");
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");

        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(new EditTaskWidget(task, dialog, this));
        dialog.setWidget(vPanel);
        dialog.setPopupPosition(400, 300);
        dialog.show();
        return dialog;
    }

    public void refresh(String nameFilter, String ownerFilter, String executerFilter, String statusFilter) {
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");
        client.getAllTasks(token, nameFilter, ownerFilter, executerFilter, statusFilter, new MethodCallback<List<TaskDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список задач: сервер не отвечает или не пройдена авторизация");
            }

            @Override
            public void onSuccess(Method method, List<TaskDto> i) {
                GWT.log("Received " + i.size() + " tasks");
                GWT.log("Status code: " + method.getResponse().getStatusCode());
                table.setRowData(i);
            }
        });
    }

    public void addTask(String name, String owner, String executer, String summary) {
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");

        client.addTask(new TaskDto(null,name,owner,executer,summary,"OPEN"), token,  new MethodCallback<TaskDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, TaskDto result) {
                refresh("","","","");
            }
        });
    }
}
