package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.AuthRequestDto;
import com.geekbrains.gwt.common.AuthResponseDto;
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
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class LoginForm extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox textUsername;

    @UiField
    TextBox textPassword;

    @UiTemplate("LoginForm.ui.xml")
    interface LoginFormBinder extends UiBinder<Widget, LoginForm> {
    }

    private static LoginForm.LoginFormBinder uiBinder = GWT.create(LoginForm.LoginFormBinder.class);

    public LoginForm() {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.form.setAction(Defaults.getServiceRoot().concat("items"));
    }

    @UiHandler("form")
    public void onSubmit(FormPanel.SubmitEvent event) {
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        Window.alert(event.getResults());
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        //AuthRequestDto authRequestDto = new AuthRequestDto(textUsername.getValue(), textPassword.getValue());
        AuthClient authClient = GWT.create(AuthClient.class);
        authClient.authenticate(textUsername.getValue(), textPassword.getValue(), new MethodCallback<String>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, String s) {
                GWT.log("OK: " + method.getResponse().getStatusCode() + " " + method.getResponse().getStatusCode());
            }
        });
    }
}