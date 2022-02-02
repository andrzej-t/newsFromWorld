package com.nfw.application.views.about;

import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        add(new H2("Welcome in NEWS FROM WORLD!!!"));
        add(new Paragraph("It is a web application supporting information search. Every week, you will find around " +
                "a million different articles from over 40,000 sources in 50+ countries. You can parameterize the data you " +
                "are interested in by selecting " +
                "the language of the article, region of the world or keywords. After selecting the appropriate filters, " +
                "just press the search button and it's ready! Links to the materials you are interested in are displayed right in front of you."));
        add(new Paragraph("This project is completely non-commercial and the use of the application is free. After adding your email as a login " +
                "and creating password, you will be able to save your favorite links (along with information about the source) on your free account. Application doesn\'t send any spam. " +
                "I hope that the tool I have prepared for you will be helpful while searching the web. Have a good fun!"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

}

