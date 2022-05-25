package com.example.application.views.admin;

import com.example.application.data.model.User;
import com.example.application.data.service.FacultyService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;

@Route(value = "faculty", layout = MainLayout.class)
@PageTitle("Faculty | Course Recommender")
@PermitAll
public class FacultyListView extends VerticalLayout {

    Grid<User> grid = new Grid<>(User.class);
    TextField filterText = new TextField();
    FacultyForm form;

    @Autowired
    private FacultyService service;

    public FacultyListView(FacultyService service) {
        this.service = service;
        addClassName("faculty-list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setFaculty(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllFaculty(filterText.getValue()));
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
        form = new FacultyForm();
        form.setWidth("25em");

        form.addListener(FacultyForm.SaveEvent.class, this::saveCourse);
        form.addListener(FacultyForm.DeleteEvent.class, this::deleteCourse);
        form.addListener(FacultyForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveCourse(FacultyForm.SaveEvent event) {
        service.saveUser(event.getUser());
        updateList();
        closeEditor();
    }

    private void deleteCourse(FacultyForm.DeleteEvent event) {
        service.deleteContact(event.getUser());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("faculty-grid");
        grid.setSizeFull();
        grid.setColumns("id","name","password", "emailID");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editFaculty(e.getValue()));
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
    }

    private void editFaculty(User value) {
        if(value == null) {
            closeEditor();
        }
        else {
            form.setFaculty(value);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addFacultyButton = new Button("Add faculty");
        addFacultyButton.addClickListener(e -> addFaculty());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addFacultyButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addFaculty() {
        grid.asSingleSelect().clear();
        editFaculty(new User());
    }
}