package com.geekbrains.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.*;

public class GwtApp implements EntryPoint {
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8189/gwt-rest");
        TasksTableWidget tasksTableWidget = new TasksTableWidget();
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(new AddTaskFormWidget(tasksTableWidget));
        verticalPanel.add(tasksTableWidget);
        RootPanel.get().add(verticalPanel);
    }
}