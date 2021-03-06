package com.nfw.application.views.search;

import com.nfw.application.domain.Publication;
import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Search")
@Route(value = "search", layout = MainLayout.class)
public class SearchView extends VerticalLayout {

    HorizontalLayout filteringLayout = new HorizontalLayout();
    TextField textField = new TextField();
    Select<String> selectLang = new Select<>();
    Select<String> selectReg = new Select<>();
    Select<String> selectCategory = new Select<>();
    Button searchingBtn = new Button("Search");
    Grid<Publication> gridResults = new Grid<>(Publication.class);

    public SearchView() {

        textField.getElement().setAttribute("aria-label", "search");
        textField.setPlaceholder("Key word...");
        textField.setClearButtonVisible(true);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        textField.setMaxLength(100);

        selectLang.setItems("", "EN", "PL");
        selectLang.setPlaceholder("Select language");
        selectLang.setEmptySelectionAllowed(true);

        selectReg.setItems("", "England", "Poland");
        selectReg.setPlaceholder("Select region");
        selectReg.setEmptySelectionAllowed(true);

        selectCategory.setItems("", "Economy", "Environment");
        selectCategory.setPlaceholder("Select category");
        selectCategory.setEmptySelectionAllowed(true);

        searchingBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "#338be9");

        filteringLayout.add(textField, selectLang, selectReg, selectCategory, searchingBtn);
        filteringLayout.getElement().getStyle().set("align", "right");
        add(filteringLayout);

        gridResults.setWidth("100%");
        gridResults.setColumns("source", "title", "description", "url", "language", "published_at");
        gridResults.setColumnOrder(gridResults.getColumns());
        gridResults.getColumnByKey("source").setSortable(false);
        gridResults.getColumnByKey("title").setSortable(false);
        gridResults.getColumnByKey("description").setSortable(false);
        gridResults.getColumnByKey("url").setSortable(false);
        gridResults.getColumnByKey("language").setSortable(false);
        gridResults.getColumnByKey("published_at").setHeader("Publication date");
        add(gridResults);
    }

}

