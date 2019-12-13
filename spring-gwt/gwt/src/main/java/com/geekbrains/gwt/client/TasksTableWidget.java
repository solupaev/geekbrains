package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.TaskDto;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
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

        client = GWT.create(TasksClient.class);

        Column<TaskDto, TaskDto> actionColumn = new Column<TaskDto, TaskDto>(
                new ActionCell<TaskDto>("REMOVE", new ActionCell.Delegate<TaskDto>() {
                    @Override
                    public void execute(TaskDto task) {
                        client.removeTask(task.getId().toString(), new MethodCallback<Void>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {
                                GWT.log(throwable.toString());
                                GWT.log(throwable.getMessage());
                            }

                            @Override
                            public void onSuccess(Method method, Void result) {
                                refresh();
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

        table.setColumnWidth(idColumn, 50, Style.Unit.PX);
        table.setColumnWidth(nameColumn, 100, Style.Unit.PX);
        table.setColumnWidth(ownerColumn, 100, Style.Unit.PX);
        table.setColumnWidth(executerColumn, 100, Style.Unit.PX);
        table.setColumnWidth(actionColumn, 200, Style.Unit.PX);

        refresh();
    }

    public void refresh() {
        client.getAllTasks(new MethodCallback<List<TaskDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список задач: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<TaskDto> i) {
                GWT.log("Received " + i.size() + " tasks");
                List<TaskDto> tasks = new ArrayList<>();
                tasks.addAll(i);
                table.setRowData( tasks);
            }
        });
    }
}
