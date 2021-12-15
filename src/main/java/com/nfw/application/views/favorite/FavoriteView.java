package com.nfw.application.views.favorite;

import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Favorite")
@Route(value = "favorite", layout = MainLayout.class)
public class FavoriteView extends VerticalLayout {

    public FavoriteView() {

    }

}
