package com.nfw.application.views.favorite;

import com.nfw.application.domain.Publication;
import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Favorite")
@Route(value = "favorite", layout = MainLayout.class)
public class FavoriteView extends VerticalLayout {

    Grid<Publication> gridFavorite = new Grid<>(Publication.class);

    public FavoriteView() {

        gridFavorite.setWidth("100%");
        gridFavorite.setColumns("source", "title", "description", "url", "language", "published_at");
        gridFavorite.setColumnOrder(gridFavorite.getColumns());
        gridFavorite.getColumnByKey("source").setSortable(false);
        gridFavorite.getColumnByKey("title").setSortable(false);
        gridFavorite.getColumnByKey("description").setSortable(false);
        gridFavorite.getColumnByKey("url").setSortable(false);
        gridFavorite.getColumnByKey("language").setSortable(false);
        gridFavorite.getColumnByKey("published_at").setHeader("Publication date");
        add(gridFavorite);

    }

}

