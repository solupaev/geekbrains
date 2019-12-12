package com.geekbrains.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.*;

public class GwtApp implements EntryPoint {
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8189/gwt-rest");
        ItemsTableWidget itemsTableWidget = new ItemsTableWidget();
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(new AddItemFormWidget(itemsTableWidget));
        verticalPanel.add(itemsTableWidget);
        RootPanel.get().add(verticalPanel);
    }
}