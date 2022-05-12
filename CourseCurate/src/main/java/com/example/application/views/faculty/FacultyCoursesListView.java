package com.example.application.views.faculty;


import com.example.application.data.model.Course;
import com.example.application.data.service.CourseService;
import com.example.application.data.service.FacultyService;
import com.example.application.data.service.StudentService;
import com.example.application.security.SecurityService;
import com.example.application.views.MainLayout;
import com.example.application.views.student.MyCoursesForm;
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

@Route(value = "FacultyCourses", layout = MainLayout.class)
@PageTitle("My Courses | Course Recommender")
@PermitAll
public class FacultyCoursesListView extends VerticalLayout {

    Grid<Course> grid = new Grid<>(Course.class);
    TextField filterText = new TextField();
    MyCoursesForm form;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private SecurityService securityService;

    public FacultyCoursesListView(CourseService courseService, FacultyService facultyService, SecurityService securityService) {
        this.courseService = courseService;
        this.facultyService = facultyService;
        this.securityService = securityService;
        addClassName("myCourseslist-view");
        setSizeFull();

        configureGrid();
        //configureForm();

        add(
                getContent()
        );

        updateList();

    }

    private void updateList() {
        grid.setItems(facultyService.getAllCourses(securityService.getAuthenticatedUser().getUser().getId()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        //content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        //form = new MyCoursesForm(courseService.findAllCourses(null), facultyService, securityService);
        form.setWidth("25em");

        form.addListener(MyCoursesForm.SaveEvent.class, this::saveUser);
    }

    private void saveUser(MyCoursesForm.SaveEvent event) {
        updateList();
    }


    private Component getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Add course");

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