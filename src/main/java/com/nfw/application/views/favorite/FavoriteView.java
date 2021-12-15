package com.nfw.application.views.favorite;

import com.nfw.application.domain.InformationDto;
import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Favorite")
@Route(value = "favorite", layout = MainLayout.class)
public class FavoriteView extends VerticalLayout {

    Grid<InformationDto> gridFavorite = new Grid<>(InformationDto.class);

    public FavoriteView() {

        gridFavorite.setWidth("100%");
        add(gridFavorite);

    }

}

