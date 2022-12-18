package com.nfw.application.views;

import com.nfw.application.views.about.AboutView;
import com.nfw.application.views.login.LoginView;
import com.nfw.application.views.search.SearchView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import java.util.ArrayList;
import java.util.List;

@PWA(name = "News From World", shortName = "News From World", enableInstallPrompt = false)
@Theme(themeFolder = "newsfromworld")
@PageTitle("Main")
@BodySize(height = "100%", width = "100%")
public class MainLayout extends AppLayout {

    public static class MenuItemInfo {

        private final String text;
        private final String iconClass;
        private final Class<? extends Component> view;

        public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
            this.text = text;
            this.iconClass = iconClass;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public String getIconClass() {
            return iconClass;
        }

        public Class<? extends Component> getView() {
            return view;
        }
    }

    public MainLayout() {
        addToNavbar(createHeaderContent());
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "flex-col", "w-full");
        header.getStyle().set("background-color", "#d2d2d2");

        Div layout = new Div();
        layout.addClassNames("flex", "h-xl", "items-center", "px-l");

        H1 appName = new H1("NEWS FROM WORLD - publication search-based application");
        appName.addClassNames("my-0", "me-auto", "text-l");
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames("flex", "gap-s", "overflow-auto", "px-m");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("flex", "list-none", "m-0", "p-0");
        nav.add(list);

        for (RouterLink link : createLinks()) {
            ListItem item = new ListItem(link);
            list.add(item);
        }

        header.add(layout, nav);
        return header;
    }

    private List<RouterLink> createLinks() {

        MenuItemInfo[] menuItems = new MenuItemInfo[]{ //
                new MenuItemInfo("LOGIN", "la la-unlock", LoginView.class), //

                new MenuItemInfo("ABOUT", "la la-info", AboutView.class), //

                new MenuItemInfo("SEARCH", "la la-search", SearchView.class), //

//                new MenuItemInfo("FAVORITE", "la la-heart", FavoriteView.class), //
        };

        List<RouterLink> links = new ArrayList<>();
        for (MenuItemInfo menuItemInfo : menuItems) {
            links.add(createLink(menuItemInfo));

        }
        return links;
    }

    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
        RouterLink link = new RouterLink();
        link.addClassNames("flex", "h-m", "items-center", "px-s", "relative", "text-secondary");
        link.setRoute(menuItemInfo.getView());
        Span icon = new Span();
        icon.addClassNames("me-s", "text-l");
        if (!menuItemInfo.getIconClass().isEmpty()) {
            icon.addClassNames(menuItemInfo.getIconClass());
        }

        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("font-medium", "text-s", "whitespace-nowrap");

        link.add(icon, text);

        return link;
    }
}