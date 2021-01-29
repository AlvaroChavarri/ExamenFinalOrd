package com.vaadin.vaadinarchetypeapplication;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
@SpringComponent
@UIScope
public class IPEditor extends VerticalLayout implements KeyNotifier{

    private final IPRepository repository;
    private IP ip;
    TextField countrycode = new TextField("Country Code");
    TextField countryname = new TextField("Country Name");
    TextField regionname= new TextField("Region Name");
    TextField cityname= new TextField("City Name");
    TextField latitude= new TextField("Latitude");
    TextField longitud= new TextField("Longitude");
    TextField zipcode= new TextField("Zip Code");
    TextField timezone= new TextField("Time Zone");
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<IP> binder = new Binder<>(IP.class);
    private ChangeHandler changeHandler;

    @Autowired
    public IPEditor(IPRepository repository) {
        this.repository = repository;

        add(countrycode, countryname,regionname,cityname,latitude,longitud,zipcode,timezone,actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editIP(ip));
        setVisible(false);
    }

    void delete() {
        repository.delete(ip);
        changeHandler.onChange();
    }

    void save() {
        repository.save(ip);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editIP(IP c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            ip = repository.findById(c.getId()).get();
        }
        else {
            ip = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(ip);

        setVisible(true);

        // Focus first name initially
        countrycode.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}
