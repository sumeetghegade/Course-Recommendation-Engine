package com.example.application.views.admin;

import com.example.application.data.service.CourseService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.example.application.data.model.Course;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;

@Route(value = "course", layout = MainLayout.class)
@PageTitle("Courses | Course Curate")
@PermitAll
public class CourseListView extends VerticalLayout {
    Grid<Course> grid = new Grid<>(Course.class);
    TextField filterText = new TextField();
    CourseForm form;
    @Autowired
    private CourseService service;

    public CourseListView(CourseService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setCourse(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllCourses(filterText.getValue()));
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
        form = new CourseForm(service.findAllUsers());
        form.setWidth("25em");

        form.addListener(CourseForm.SaveEvent.class, this::saveCourse);
        form.addListener(CourseForm.DeleteEvent.class, this::deleteCourse);
        form.addListener(CourseForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveCourse(CourseForm.SaveEvent event) {
        service.saveCourse(event.getCourse());
        updateList();
        closeEditor();
    }

    private void deleteCourse(CourseForm.DeleteEvent event) {
        service.deleteContact(event.getCourse());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("course-grid");
        grid.setSizeFull();
        grid.setColumns("id","name");
        grid.addColumn(course -> course.getFaculty() == null ? "":course.getFaculty().getName()).setHeader("Faculty");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editCourse(e.getValue()));
    }

    private void editCourse(Course value) {
        if(value == null) {
            closeEditor();
        }
        else {
            form.setCourse(value);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add course");
        addContactButton.addClickListener(e -> addContact());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editCourse(new Course());
    }
}
