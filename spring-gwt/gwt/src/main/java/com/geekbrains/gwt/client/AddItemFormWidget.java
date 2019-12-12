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

public class AddItemFormWidget extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox idText;

    @UiField
    TextBox titleText;

    private ItemsTableWidget itemsTableWidget;

    @UiTemplate("AddItemForm.ui.xml")
    interface AddItemFormBinder extends UiBinder<Widget, AddItemFormWidget> {
    }

    private static AddItemFormWidget.AddItemFormBinder uiBinder = GWT.create(AddItemFormWidget.AddItemFormBinder.class);

    public AddItemFormWidget(ItemsTableWidget itemsTableWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.form.setAction(Defaults.getServiceRoot().concat("items"));
        this.itemsTableWidget = itemsTableWidget;
    }

    @UiHandler("form")
    public void onSubmit(FormPanel.SubmitEvent event) {
        if (idText.getText().length() == 0) {
            Window.alert("Необходимо заполнить поле ID");
            event.cancel();
        }
        if (titleText.getText().length() < 4) {
            Window.alert("Название товара должно быть не менее 4 символов");
            event.cancel();
        }
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        itemsTableWidget.refresh();
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        form.submit();
    }
}
