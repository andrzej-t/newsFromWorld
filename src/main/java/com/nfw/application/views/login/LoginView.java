package com.nfw.application.views.login;

import com.nfw.application.client.BackendClient;
import com.nfw.application.domain.LoginRequest;
import com.nfw.application.domain.RegistrationRequest;
import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class LoginView extends HorizontalLayout {

    @Autowired
    BackendClient backendClient;
    @Autowired
    RegistrationRequest registrationRequest;
    @Autowired
    LoginRequest loginRequest;

    VerticalLayout loginLayout = new VerticalLayout();
    Component textLogin = new Text("LOG INTO YOUR ACCOUNT: ");
    TextField email = new TextField();
    PasswordField password = new PasswordField();
    Button loginBtn = new Button("Log in", buttonClickEvent -> {
        backendClient.login(loginRequest);
    });
    Button logoutBtn = new Button("Log out", buttonClickEvent -> {
        backendClient.logout(loginRequest);
        email.clear();
        password.clear();
    });
    HorizontalLayout logBtns = new HorizontalLayout();

    VerticalLayout signInLayout = new VerticalLayout();
    Component textSignIn = new Text("CREATE FREE ACCOUNT: ");
    TextField nameS = new TextField();
    TextField surnameS = new TextField();
    TextField emailS = new TextField();
    PasswordField passwordS = new PasswordField();
    Button signInBtn = new Button("Create ", buttonClickEvent -> {
        backendClient.registerAccount(registrationRequest);
        nameS.clear();
        surnameS.clear();
        emailS.clear();
        passwordS.clear();
    });
    Button deleteBtn = new Button("Delete");
    HorizontalLayout signBtns = new HorizontalLayout();

    public LoginView() {

        email.setPrefixComponent(VaadinIcon.USER.create());
        email.setLabel("Email:");
        email.setAutocomplete(Autocomplete.OFF);
        email.addValueChangeListener(event -> loginRequest.setEmail(email.getValue()));

        password.setLabel("Password:");
        password.setAutocomplete(Autocomplete.OFF);
        password.addValueChangeListener(event -> loginRequest.setPassword(password.getValue()));

        loginBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "#338be9");

        nameS.setLabel("Your name:");
        nameS.setAutocomplete(Autocomplete.OFF);
        nameS.addValueChangeListener(event -> registrationRequest.setName(nameS.getValue()));

        surnameS.setLabel("Your surname:");
        surnameS.setAutocomplete(Autocomplete.OFF);
        surnameS.addValueChangeListener(event -> registrationRequest.setSurname(surnameS.getValue()));

        emailS.setPrefixComponent(VaadinIcon.USER.create());
        emailS.setLabel("Your email:");
        emailS.setAutocomplete(Autocomplete.OFF);
        emailS.addValueChangeListener(event -> registrationRequest.setEmail(emailS.getValue()));

        passwordS.setLabel("Insert password:");
        passwordS.setAutocomplete(Autocomplete.OFF);
        passwordS.addValueChangeListener(event -> registrationRequest.setPassword(passwordS.getValue()));

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

