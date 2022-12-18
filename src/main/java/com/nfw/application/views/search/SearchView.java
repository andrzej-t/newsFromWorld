package com.nfw.application.views.search;

import com.nfw.application.client.BackendClient;
import com.nfw.application.domain.LoginRequest;
import com.nfw.application.domain.PublicationDto;
import com.nfw.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.Objects;

import static oshi.util.Util.sleep;

@PageTitle("Search")
@Route(value = "search", layout = MainLayout.class)
public class SearchView extends VerticalLayout {

    PublicationDto publicationDto;
    BackendClient backendClient;
    LoginRequest loginRequest;
    HorizontalLayout filteringLayout = new HorizontalLayout();
    TextField textField = new TextField();
    Select<String> selectLang = new Select<>();
    Select<String> selectCategory = new Select<>();
    Button searchingBtn = new Button("Find");
    Button addToFavBtn = new Button("Add");
    Grid<PublicationDto> gridResults = new Grid<>(PublicationDto.class);
    HorizontalLayout pageNavLayout = new HorizontalLayout();
    IntegerField pageNumber = new IntegerField("Page number: ", "1");
    Select<String> selectDay = new Select<>();
    Select<String> selectMonth = new Select<>();
    Select<String> selectYear = new Select<>();
    VerticalLayout notificationLayout = new VerticalLayout();
    Span notificationContent = new Span();
    NativeButton notificationBtn = new NativeButton("Close");
    Notification generalNotification = new Notification(notificationLayout);

    @Autowired
    public PublicationDto getPublicationDto() {
        return publicationDto;
    }

    @Autowired
    public BackendClient getBackendClient() {
        return backendClient;
    }

    @Autowired
    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setDays(String monthValue, String dayValue) {

        if (monthValue.equals("02")) {
            selectDay.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28");
            if (!selectDay.isEmpty()) {
                selectDay.setValue(dayValue);
            } else {
                selectDay.setValue(LocalDate.now().toString().substring(8));
            }
        }
        if (monthValue.equals("04") || selectMonth.getValue().equals("06") || selectMonth.getValue().equals("09") || selectMonth.getValue().equals("11")) {
            selectDay.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30");
            if (!selectDay.isEmpty()) {
                selectDay.setValue(dayValue);
            } else {
                selectDay.setValue(LocalDate.now().toString().substring(8));
            }
        }
        if (monthValue.equals("01") || selectMonth.getValue().equals("03") || selectMonth.getValue().equals("05") || selectMonth.getValue().equals("07") || selectMonth.getValue().equals("08") || selectMonth.getValue().equals("10") || selectMonth.getValue().equals("12")) {
            selectDay.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
            if (!selectDay.isEmpty()) {
                selectDay.setValue(dayValue);
            } else {
                selectDay.setValue(LocalDate.now().toString().substring(8));
            }
        }
    }

    public String choseLanguage(String langName) {
        if (langName.equals("Select all")) langName = "";
        if (langName.equals("Arabic")) langName = "ar";
        if (langName.equals("Bulgarian")) langName = "bg";
        if (langName.equals("Chinese")) langName = "zh";
        if (langName.equals("Croatian")) langName = "hr";
        if (langName.equals("Czech")) langName = "cs";
        if (langName.equals("Danish")) langName = "da";
        if (langName.equals("Dutch")) langName = "du";
        if (langName.equals("English")) langName = "en";
        if (langName.equals("Estonian")) langName = "et";
        if (langName.equals("French")) langName = "fr";
        if (langName.equals("German")) langName = "de";
        if (langName.equals("Greek")) langName = "el";
        if (langName.equals("Hebrew")) langName = "he";
        if (langName.equals("Hindi")) langName = "hi";
        if (langName.equals("Hungarian")) langName = "hu";
        if (langName.equals("Italian")) langName = "it";
        if (langName.equals("Lithuanian")) langName = "lt";
        if (langName.equals("Japan")) langName = "ja";
        if (langName.equals("Korean")) langName = "ko";
        if (langName.equals("Persian")) langName = "fa";
        if (langName.equals("Polish")) langName = "pl";
        if (langName.equals("Portuguese")) langName = "pt";
        if (langName.equals("Romanian")) langName = "ro";
        if (langName.equals("Russian")) langName = "ru";
        if (langName.equals("Slovak")) langName = "sk";
        if (langName.equals("Spanish")) langName = "es";
        if (langName.equals("Swahili")) langName = "sw";
        if (langName.equals("Swedish")) langName = "sv";
        if (langName.equals("Turkish")) langName = "tr";
        if (langName.equals("Ukrainian")) langName = "uk";
        return langName;
    }

    public String choseCategory(String categoryName) {
        if (categoryName.equals("Select all")) categoryName = "";

        return categoryName;
    }

    public SearchView(BackendClient backendClient, LoginRequest loginRequest) {

        sleep(2000);
        notificationLayout.add(notificationContent, notificationBtn);
        generalNotification.setPosition(Notification.Position.MIDDLE);
        notificationContent.getStyle().set("text-align", "center");
        generalNotification.setDuration(3000);
        notificationBtn.addClickListener(event -> {
            generalNotification.close();
            UI.getCurrent().navigate("/login");
        });
        notificationBtn.getElement().getStyle()
                .set("position", "relative")
                .set("left", "35%")
                .set("color", "#f3f5f7")
                .set("background", "#014af3");

        boolean isLocked = backendClient.isUserLocked(loginRequest);

        if (isLocked) {
            notificationContent.setText("You must be logged in before using browser.");
            generalNotification.setOpened(true);
            textField.setReadOnly(true);
            searchingBtn.setEnabled(false);
            selectLang.setEnabled(false);
            selectCategory.setEnabled(false);
            selectDay.setEnabled(false);
            selectMonth.setEnabled(false);
            selectYear.setEnabled(false);
            gridResults.setEnabled(false);
            pageNumber.setEnabled(false);
        } else {
            generalNotification.setOpened(false);
            textField.setReadOnly(false);
            searchingBtn.setEnabled(true);
            selectLang.setEnabled(true);
            selectCategory.setEnabled(true);
            selectDay.setEnabled(true);
            selectMonth.setEnabled(true);
            selectYear.setEnabled(true);
            gridResults.setEnabled(true);
            pageNumber.setEnabled(true);
        }

//        addToFavBtn.addClickListener(a -> {
//        });

        selectMonth.setLabel("month");
        selectMonth.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        selectMonth.setValue(LocalDate.now().toString().substring(5, 7));
        selectMonth.addValueChangeListener(selectStringComponentValueChangeEvent -> setDays(selectMonth.getValue(), selectDay.getValue()));

        selectDay.setLabel("Published before: day");
        selectDay.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        selectDay.setValue(LocalDate.now().toString().substring(8));
        setDays(LocalDate.now().toString().substring(5, 7), LocalDate.now().toString().substring(8));

        selectYear.setLabel("year");
        selectYear.setItems("2023", "2022", "2021");
        selectYear.setValue(LocalDate.now().toString().substring(0, 4));

        textField.getElement().setAttribute("aria-label", "search");
        textField.setPlaceholder("key word");
        textField.setLabel("Search...");
        textField.setClearButtonVisible(false);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        textField.setMaxLength(100);

        selectLang.setItems("Select all", "Arabic", "Bulgarian", "Chinese", "Croatian", "Czech", "Danish", "Dutch", "English", "Estonian",
                "French", "German", "Greek", "Hebrew", "Hindi", "Hungarian", "Italian", "Lithuanian", "Japan", "Korean",
                "Persian", "Polish", "Portuguese", "Romanian", "Russian", "Slovak", "Spanish", "Swahili", "Swedish", "Turkish", "Ukrainian");
        selectLang.setLabel("Select language: ");
        selectLang.setValue("Polish");
        selectLang.setEmptySelectionAllowed(false);

        selectCategory.setItems("Select all", "business", "entertainment", "food", "general", "health", "politics", "science", "sports", "tech", "travel");
        selectCategory.setLabel("Select category: ");
        selectCategory.setValue("Select all");
        selectCategory.setEmptySelectionAllowed(false);

        searchingBtn.getElement().getStyle()
                .set("color", "#ffffff")
                .set("background", "#338be9")
                .set("margin", "3% 3% 1% 3%");

        searchingBtn.addClickListener(buttonClickEvent -> {
            try {
                gridResults.setItems(backendClient.fetchResults(textField.getValue(), choseLanguage(selectLang.getValue()), choseCategory(selectCategory.getValue()), pageNumber.getValue(), selectYear.getValue() + "-" + selectMonth.getValue() + "-" + selectDay.getValue(), loginRequest));
            } catch (HttpClientErrorException e) {
                generalNotification.setOpened(true);
                notificationContent.setText(Objects.requireNonNull(e.getMessage()).substring(7, e.getMessage().length() - 1));
                textField.setReadOnly(true);
                searchingBtn.setEnabled(false);
                selectLang.setEnabled(false);
                selectCategory.setEnabled(false);
                selectDay.setEnabled(false);
                selectMonth.setEnabled(false);
                selectYear.setEnabled(false);
                gridResults.setEnabled(false);
                pageNumber.setEnabled(false);
            }
        });


        filteringLayout.add(textField, searchingBtn, selectLang, selectCategory, selectDay, selectMonth, selectYear);
        add(filteringLayout);

        gridResults.setWidth("100%");
        gridResults.setColumns("source", "title", "language", "published_at");
        gridResults.setColumnOrder(gridResults.getColumns());
        gridResults.getColumnByKey("source").setSortable(false);
        gridResults.getColumnByKey("title").setSortable(false);
        gridResults.getColumnByKey("language").setSortable(false);
        gridResults.getColumnByKey("published_at").setHeader("Publication date");
        try {
            gridResults.setItems(backendClient.fetchResults(textField.getValue(), choseLanguage(selectLang.getValue()), choseCategory(selectCategory.getValue()), 1, selectYear.getValue() + "-" + selectMonth.getValue() + "-" + selectDay.getValue(), loginRequest));
        } catch (HttpClientErrorException e) {
            generalNotification.setOpened(true);
            notificationContent.setText(Objects.requireNonNull(e.getMessage()).substring(7, e.getMessage().length() - 1));
            textField.setReadOnly(true);
            searchingBtn.setEnabled(false);
            selectLang.setEnabled(false);
            selectCategory.setEnabled(false);
            selectDay.setEnabled(false);
            selectMonth.setEnabled(false);
            selectYear.setEnabled(false);
            gridResults.setEnabled(false);
            pageNumber.setEnabled(false);
        }

        gridResults.setItemDetailsRenderer(TemplateRenderer.<PublicationDto>of(
                        "<div class='custom-details' style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
                                + "<b>Address:</b> <div>[[item.url]]</div><br />"
                                + "<b>Title:</b> <div>[[item.title]]</div><br />"
                                + "<b>Description:</b> <div>[[item.description]]</div><br />"
                                + "<b>Full text:</b> <div><a href=[[item.url]] target=_blank>Click here!</a></div><br />"
                                + "</div>")
                .withProperty("url", PublicationDto::getUrl)
                .withProperty("title", PublicationDto::getTitle)
                .withProperty("description", PublicationDto::getDescription)
                .withEventHandler("handleClick", publicationDto -> gridResults.getDataProvider().refreshItem(publicationDto)));
        gridResults.setDetailsVisibleOnClick(false);
        gridResults.addColumn(new NativeButtonRenderer<>("Description", item -> gridResults.setDetailsVisible(item, !gridResults.isDetailsVisible(item)))).setHeader("Description");

        // TODO IN FUTURE: Code below is for new functionality (adding to favourite)
//        gridResults.addColumn(
//                new ComponentRenderer<>(Button::new, ((button, publicationDto) -> {
////                    button.addThemeVariants(
////                            ButtonVariant.LUMO_ICON,
////                            ButtonVariant.LUMO_ERROR,
////                            ButtonVariant.LUMO_TERTIARY);
//                    button.setIcon(new Icon(VaadinIcon.HEART));
//                }))).setHeader("Add to favorite");

        add(gridResults);

        pageNumber.setHasControls(true);
        pageNumber.setMin(1);
        pageNumber.setMax(1000);
        pageNumber.setValue(1);
        pageNumber.setHelperText("max. 1000 pages");
        pageNavLayout.add(pageNumber);
        pageNavLayout.setWidth("100%");
        pageNavLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        try {
            pageNumber.addValueChangeListener(event -> {
                gridResults.setItems(backendClient.fetchResults(textField.getValue(), choseLanguage(selectLang.getValue()), choseCategory(selectCategory.getValue()), pageNumber.getValue(), selectYear.getValue() + "-" + selectMonth.getValue() + "-" + selectDay.getValue(), loginRequest));
            });
        } catch (HttpClientErrorException e) {
            generalNotification.setOpened(true);
            notificationContent.setText(Objects.requireNonNull(e.getMessage()).substring(7, e.getMessage().length() - 1));
            textField.setReadOnly(true);
            searchingBtn.setEnabled(false);
            selectLang.setEnabled(false);
            selectCategory.setEnabled(false);
            selectDay.setEnabled(false);
            selectMonth.setEnabled(false);
            selectYear.setEnabled(false);
            gridResults.setEnabled(false);
            pageNumber.setEnabled(false);
        }
        add(pageNavLayout);
    }
}