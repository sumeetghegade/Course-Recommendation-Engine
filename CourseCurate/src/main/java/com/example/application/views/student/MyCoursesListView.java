package com.example.application.views.student;


import com.example.application.data.model.Course;
import com.example.application.data.service.CourseService;
import com.example.application.data.service.StudentService;
import com.example.application.security.SecurityService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;

@Route(value = "myCourses", layout = MainLayout.class)
@PageTitle("My Courses | Course Recommender")
@PermitAll
public class MyCoursesListView extends VerticalLayout {

    Grid<Course> grid = new Grid<>(Course.class);
    TextField filterText = new TextField();
    MyCoursesForm form;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SecurityService securityService;

    public MyCoursesListView(CourseService courseService, StudentService studentService, SecurityService securityService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.securityService = securityService;
        addClassName("myCourseslist-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getContent()
        );

        updateList();

    }

    private void updateList() {
        grid.setItems(studentService.getAllCourses(securityService.getAuthenticatedUser().getUser().getId()));
    }

    private Component getContent() {
    	
    	H6 gridTitle = new H6("Your Selected Courses");
    	gridTitle.getStyle().set("margin-top", "0");
    	
    	H6 formTitle = new H6("All Courses");
    	formTitle.getStyle().set("margin-top", "0");
    	
    	// https://stackoverflow.com/questions/55735600/vaadin-13-flex-grow -- for sizing
    	VerticalLayout gridWithTitle = new VerticalLayout(gridTitle, grid);
    	gridWithTitle.setWidth(null);
    	gridWithTitle.setPadding(false);
    	gridWithTitle.setMargin(false);
    	
    	VerticalLayout formWithTitle = new VerticalLayout(formTitle, form);
    	formWithTitle.setWidth(null);
    	formWithTitle.setPadding(false);
    	formWithTitle.setMargin(false);
    	
    	HorizontalLayout content = new HorizontalLayout(gridWithTitle, formWithTitle);
        content.setFlexGrow(2.0, gridWithTitle);
        content.setFlexGrow(0, formWithTitle);
        content.addClassName("content");
        content.setSizeFull();
        content.setMargin(false);
        
        return content;
    }

    private void configureForm() {
        form = new MyCoursesForm(courseService.findAllCourses(null), studentService, securityService);
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