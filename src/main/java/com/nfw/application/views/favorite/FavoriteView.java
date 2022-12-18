package com.nfw.application.views.favorite;

import com.nfw.application.client.BackendClient;
import com.nfw.application.domain.PublicationDto;
import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Favorite")
@Route(value = "favorite", layout = MainLayout.class)
public class FavoriteView extends VerticalLayout {

    BackendClient backendClient;

    @Autowired
    public BackendClient getBackendClient() {
        return backendClient;
    }

    Grid<PublicationDto> gridFavorite = new Grid<>(PublicationDto.class);
    IntegerField pageNumber = new IntegerField("Page number: ", "1");

    public FavoriteView(BackendClient backendClient) {

        gridFavorite.setWidth("100%");
        gridFavorite.setColumns("source", "title", "language", "published_at");
        gridFavorite.setColumnOrder(gridFavorite.getColumns());
        gridFavorite.getColumnByKey("source").setSortable(false);
        gridFavorite.getColumnByKey("title").setSortable(false);
        gridFavorite.getColumnByKey("language").setSortable(false);
        gridFavorite.getColumnByKey("published_at").setHeader("PublicationDto date");
//        gridFavorite.setItems(backendClient.fetchFavorite());
        add(gridFavorite);
    }
}
