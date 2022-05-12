package com.example.application.views.admin;


import com.example.application.data.model.Domain;
import com.example.application.data.service.DomainService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;

@Route(value = "domain", layout = MainLayout.class)
@PageTitle("Domains | Course Recommender")
@PermitAll
public class DomainListView extends VerticalLayout {

    Grid<Domain> grid = new Grid<>(Domain.class);
    TextField filterText = new TextField();
    DomainForm form;

    @Autowired
    private DomainService service;

    public DomainListView(DomainService service) {
        this.service = service;
        addClassName("domain-list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setDomain(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllDomains(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new DomainForm();
        form.setWidth("25em");

        form.addListener(DomainForm.SaveEvent.class, this::saveDomain);
        form.addListener(DomainForm.DeleteEvent.class, this::deleteDomain);
        form.addListener(DomainForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveDomain(DomainForm.SaveEvent event) {
        service.saveDomain(event.getDomain());
        updateList();
        closeEditor();
    }

    private void deleteDomain(DomainForm.DeleteEvent event) {
        service.deleteDomain(event.getDomain());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("domain-grid");
        grid.setSizeFull();
        grid.setColumns("id","name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editDomain(e.getValue()));
    }

    private void editDomain(Domain value) {
        if(value == null) {
            closeEditor();
        }
        else {
            form.setDomain(value);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addDomainButton = new Button("Add Domain");
        addDomainButton.addClickListener(e -> addDomain());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addDomainButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addDomain() {
        grid.asSingleSelect().clear();
        editDomain(new Domain());
    }
}

