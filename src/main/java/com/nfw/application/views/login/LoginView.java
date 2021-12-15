package com.nfw.application.views.login;

import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class LoginView extends HorizontalLayout {

    VerticalLayout loginLayout = new VerticalLayout();
    Component textLogin = new Text("LOG INTO YOUR ACCOUNT: ");
    TextField username = new TextField();
    TextField password = new TextField();
    Button loginBtn = new Button("Log in");
    Button logoutBtn = new Button("Log out");
    HorizontalLayout logBtns = new HorizontalLayout();

    VerticalLayout signInLayout = new VerticalLayout();
    Component textSignIn = new Text("CREATE FREE ACCOUNT: ");
    TextField emailS = new TextField();
    TextField usernameS = new TextField();
    TextField passwordS = new TextField();
    Button signInBtn = new Button("Create ");
    Button deleteBtn = new Button("Delete");
    HorizontalLayout signBtns = new HorizontalLayout();

    public LoginView() {

        username.setPrefixComponent(VaadinIcon.USER.create());
        username.setLabel("Username");
        username.setValue("");
        password.setLabel("Password");
        password.setValue("");
        loginBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "#338be9");

        usernameS.setPrefixComponent(VaadinIcon.USER.create());
        emailS.setLabel("Insert your email");
        usernameS.setLabel("Insert username");
        usernameS.setValue("");
        passwordS.setLabel("Insert password");
        passwordS.setValue("");
        signInBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "#338be9");
        deleteBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "red");
        deleteBtn.setVisible(false);

        logBtns.add(loginBtn, logoutBtn);
        loginLayout.add(textLogin, username, password, logBtns);
        loginLayout.getElement().getStyle().set("padding", "20px");
        loginLayout.getElement().getStyle().set("margin", "50px 0px 0px 50px");

        signBtns.add(signInBtn, deleteBtn);
        signInLayout.add(textSignIn, emailS, usernameS, passwordS, signBtns);
        signInLayout.getElement().getStyle().set("padding", "20px");
        signInLayout.getElement().getStyle().set("margin", "50px 0px 0px 50px");

        add(loginLayout);
        add(signInLayout);

    }

}

