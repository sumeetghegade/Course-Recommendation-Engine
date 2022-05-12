package com.example.application.views.admin;


import com.example.application.data.model.User;
import com.example.application.data.service.StudentService;
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

@Route(value = "student", layout = MainLayout.class)
@PageTitle("Students | Course Recommender")
@PermitAll
public class StudentListView extends VerticalLayout {

    Grid<User> grid = new Grid<>(User.class);
    TextField filterText = new TextField();
    StudentForm form;

    @Autowired
    private StudentService service;

    public StudentListView(StudentService service) {
        this.service = service;
        addClassName("student-list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setStudent(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllStudents(filterText.getValue()));
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
        form = new StudentForm();
        form.setWidth("25em");

        form.addListener(StudentForm.SaveEvent.class, this::saveCourse);
        form.addListener(StudentForm.DeleteEvent.class, this::deleteCourse);
        form.addListener(StudentForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveCourse(StudentForm.SaveEvent event) {
        service.saveUser(event.getUser());
        updateList();
        closeEditor();
    }

    private void deleteCourse(StudentForm.DeleteEvent event) {
        service.deleteContact(event.getUser());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("student-grid");
        grid.setSizeFull();
        grid.setColumns("id","name","password", "emailID");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editStudent(e.getValue()));
    }

    private void editStudent(User value) {
        if(value == null) {
            closeEditor();
        }
        else {
            form.setStudent(value);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addStudentButton = new Button("Add student");
        addStudentButton.addClickListener(e -> addStudent());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addStudentButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addStudent() {
        grid.asSingleSelect().clear();
        editStudent(new User());
    }
}
