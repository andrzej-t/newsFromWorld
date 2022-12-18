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

        add(new H2("Welcome to NEWS FROM WORLD!!!"));
        add(new Paragraph("This is a publication search-based application. Every week, you will find around " +
                "a million different articles from over 40,000 sources in 50+ countries. You can parameterize the data you " +
                "are interested in by selecting " +
                "the language of the article, category, keywords or publishing date (over the past two years). After creating free account and selecting the appropriate filters, " +
                "just press the search button and it's ready! Links to the materials you are interested in are displayed right in front of you."));

        add(new Paragraph("\"News From World\" was created as a hobby project through which I learn java programming. " +
                "In the future I plan to develop it with additional functionalities. Since I am using a free api, the application has a daily query limit and yet it is only 150. " +
                "I hope to increase it in the future. Application doesn't send any spam. " +
                "I hope that the tool I have prepared for you will be helpful while searching the web. Have a good fun!"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}
