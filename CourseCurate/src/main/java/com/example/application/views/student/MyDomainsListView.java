package com.example.application.views.student;

import com.example.application.data.model.Domain;
import com.example.application.data.service.DomainService;
import com.example.application.data.service.StudentService;
import com.example.application.security.SecurityService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
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
import java.util.Collections;


@Route(value = "myDomains", layout = MainLayout.class)
@PageTitle("My Domains | Course Recommender")
@PermitAll
public class MyDomainsListView extends VerticalLayout {


    Grid<Domain> grid = new Grid<>(Domain.class);
    TextField filterText = new TextField();
    MyDomainsForm form;
    @Autowired
    private DomainService domainService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SecurityService securityService;

    public MyDomainsListView(DomainService domainService, StudentService studentService, SecurityService securityService) {
        this.domainService = domainService;
        this.studentService = studentService;
        this.securityService = securityService;
        addClassName("myDomainslist-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getContent()
        );

        updateList();

    }

    private void updateList() {
        grid.setItems(studentService.getAllDomains(securityService.getAuthenticatedUser().getUser().getId()));
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
        form = new MyDomainsForm(domainService.findAllDomains(null), studentService, securityService);
        form.setWidth("25em");

        form.addListener(MyDomainsForm.SaveEvent.class, this::saveUser);
    }

    private void saveUser(MyDomainsForm.SaveEvent event) {
        updateList();
    }


    private Component getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Add domain");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassNames("list-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

}