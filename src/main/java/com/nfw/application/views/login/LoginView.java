package com.nfw.application.views.login;

import com.nfw.application.client.BackendClient;
import com.nfw.application.domain.LoginRequest;
import com.nfw.application.domain.RegistrationRequest;
import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

import static oshi.util.Util.sleep;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class LoginView extends HorizontalLayout {

    BackendClient backendClient;
    RegistrationRequest registrationRequest;
    LoginRequest loginRequest;
    VerticalLayout loginLayout = new VerticalLayout();
    Component textLogin = new Text("LOG INTO YOUR ACCOUNT: ");
    TextField email = new TextField();
    PasswordField password = new PasswordField();
    Button loginBtn = new Button("Log in");
    Button logoutBtn = new Button("Log out");
    HorizontalLayout logBtns = new HorizontalLayout();
    VerticalLayout signInLayout = new VerticalLayout();
    Component textSignIn = new Text("CREATE ACCOUNT: ");
    TextField nameS = new TextField();
    TextField surnameS = new TextField();
    TextField emailS = new TextField();
    PasswordField passwordS = new PasswordField();
    VerticalLayout notificationLayout = new VerticalLayout();
    Span notificationContent = new Span();
    NativeButton notificationBtn = new NativeButton("Close");
    Notification generalNotification = new Notification(notificationLayout);
    Button signInBtn = new Button("Create");
    Button deleteBtn = new Button("Delete");
    HorizontalLayout signBtns = new HorizontalLayout();

    @Autowired
    public BackendClient getBackendClient() {
        return backendClient;
    }
    @Autowired
    public RegistrationRequest getRegistrationRequest() {
        return registrationRequest;
    }
    @Autowired
    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public LoginView(BackendClient backendClient, RegistrationRequest registrationRequest, LoginRequest loginRequest) {

        sleep(2000);
        notificationLayout.add(notificationContent, notificationBtn);
        generalNotification.setPosition(Notification.Position.MIDDLE);
        generalNotification.setDuration(3000);
        notificationContent.getStyle().set("text-align", "center");
        notificationBtn.getStyle().set("position", "relative");
        notificationBtn.getStyle().set("left", "35%");
        notificationBtn.addClickListener(event -> generalNotification.close());
        notificationBtn.getElement().getStyle()
                .set("color", "#f3f5f7")
                .set("background", "#014af3")
                .set("align", "center");

        email.setPrefixComponent(VaadinIcon.USER.create());
        email.setRequired(true);
        email.setLabel("Email:");
        email.setAutocomplete(Autocomplete.OFF);
        email.addValueChangeListener(event -> loginRequest.setEmail(email.getValue()));

        password.setLabel("Password:");
        password.setRequired(true);
        password.setAutocomplete(Autocomplete.OFF);
        password.addValueChangeListener(event -> loginRequest.setPassword(password.getValue()));

        loginBtn.addClickListener(buttonClickEvent -> {
            email.addValueChangeListener(event -> loginRequest.setEmail(email.getValue()));
            password.addValueChangeListener(event -> loginRequest.setPassword(password.getValue()));
            try {
                backendClient.login(loginRequest);
                email.setVisible(true);
                UI.getCurrent().getPage().setLocation("/search");
            } catch (HttpClientErrorException e) {
                generalNotification.setOpened(true);
                notificationContent.setText(Objects.requireNonNull(e.getMessage()).substring(7, e.getMessage().length() - 1));
            }
        });
        loginBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "#338be9");

        logoutBtn.addClickListener(buttonClickEvent -> {
            backendClient.logout(loginRequest);
            loginRequest.setEmail("");
            loginRequest.setPassword("");
            email.clear();
            password.clear();
            generalNotification.setOpened(true);
            notificationContent.setText("You are logged out.");
        });
        logoutBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "grey");

        nameS.setLabel("Your name:");
        nameS.setAutocomplete(Autocomplete.OFF);
        nameS.setRequired(true);
        nameS.addValueChangeListener(event -> registrationRequest.setName(nameS.getValue()));

        surnameS.setLabel("Your surname:");
        surnameS.setRequired(true);
        surnameS.setAutocomplete(Autocomplete.OFF);
        surnameS.addValueChangeListener(event -> registrationRequest.setSurname(surnameS.getValue()));

        emailS.setPrefixComponent(VaadinIcon.USER.create());
        emailS.setRequired(true);
        emailS.setLabel("Your email:");
        emailS.setAutocomplete(Autocomplete.OFF);
        emailS.addValueChangeListener(event -> registrationRequest.setEmail(emailS.getValue()));

        passwordS.setLabel("Insert password:");
        passwordS.setRequired(true);
        passwordS.setAutocomplete(Autocomplete.OFF);
        passwordS.addValueChangeListener(event -> registrationRequest.setPassword(passwordS.getValue()));

        signInBtn.addClickListener(buttonClickEvent -> {

            try {
                backendClient.registerAccount(registrationRequest);
                nameS.setValue("");
                surnameS.setValue("");
                emailS.setValue("");
                passwordS.setValue("");
                nameS.clear();
                surnameS.clear();
                emailS.clear();
                passwordS.clear();
                generalNotification.setOpened(true);
                notificationContent.setText("Please check your email and click link to activate your account");
            } catch (HttpClientErrorException e) {
                nameS.setValue("");
                surnameS.setValue("");
                emailS.setValue("");
                passwordS.setValue("");
                nameS.clear();
                surnameS.clear();
                emailS.clear();
                passwordS.clear();
                generalNotification.setOpened(true);
                notificationContent.setText(Objects.requireNonNull(e.getMessage()).substring(7, e.getMessage().length() - 1));
            }
        });
        signInBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "#338be9");
        deleteBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "red");
        deleteBtn.setVisible(false);

        logBtns.add(loginBtn, logoutBtn);
        loginLayout.setSpacing(false);
        loginLayout.add(textLogin, email, password, logBtns);
        loginLayout.getElement().getStyle().set("padding", "20px");
        loginLayout.getElement().getStyle().set("margin", "50px 0px 0px 50px");

        signBtns.add(signInBtn, deleteBtn);
        signInLayout.setSpacing(false);
        signInLayout.add(textSignIn, nameS, surnameS, emailS, passwordS, signBtns);
        signInLayout.getElement().getStyle().set("padding", "20px");
        signInLayout.getElement().getStyle().set("margin", "50px 0px 0px 50px");

        add(loginLayout);
        add(signInLayout);
    }
}