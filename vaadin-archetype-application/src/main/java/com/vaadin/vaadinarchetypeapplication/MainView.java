package com.vaadin.vaadinarchetypeapplication;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.util.StringUtils;
@Route
public class MainView extends VerticalLayout {
    private final IPRepository repo;

    private final IPEditor editor;

    final Grid<IP> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(IPRepository repo, IPEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(IP.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New ip", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "countrycode", "countryname","regionname","cityname","latitude","longitud","zipcode","timezone");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by countrycode");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listIP(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editIP(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editIP(new IP("", "","","","","","","")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listIP(filter.getValue());
        });

        // Initialize listing
        listIP(null);
    }

    // tag::listCustomers[]
    void listIP(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
    }
    // end::listCustomers[]

}
