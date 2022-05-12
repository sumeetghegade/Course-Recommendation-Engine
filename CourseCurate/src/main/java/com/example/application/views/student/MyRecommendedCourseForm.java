package com.example.application.views.student;

import com.example.application.data.model.Course;
import com.example.application.data.model.Domain;
import com.example.application.data.service.CourseService;
import com.example.application.data.service.StudentService;
import com.example.application.security.SecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRecommendedCourseForm extends FormLayout {

    MultiSelectListBox<Course> course = new MultiSelectListBox<>();


    Button add = new Button("Add");
    Button cancel = new Button("Cancel");

    private StudentService studentService;
    private SecurityService securityService;


    public MyRecommendedCourseForm(StudentService studentService, SecurityService securityService, CourseService courseService) {

        Set<Domain> domains = studentService.getAllDomains(securityService.getAuthenticatedUser().getUser().getId());
        Set<String> courses = studentService.getRecommendations(domains);
        Set<Course> coursesObj = new HashSet<>();
        this.studentService = studentService;
        this.securityService = securityService;
        for(String s: courses) {
            coursesObj.add(courseService.findByName(s));
        }
        course.setItems(coursesObj);
        course.setItemLabelGenerator(Course::getName);
        add(
                course,
                createButtonLayout()
        );
    }

    private Component createButtonLayout() {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        add.addClickListener(event -> validateAndAdd(this));
        cancel.addClickListener(event -> fireEvent(new MyRecommendedCourseForm.CloseEvent(this)));

        add.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(add, cancel);
    }

    private void validateAndAdd(MyRecommendedCourseForm form) {
        Set<Course> selectedCourses = form.course.getSelectedItems();
        studentService.saveAllCourses(form.securityService.getAuthenticatedUser().getUser().getId(), selectedCourses);
        fireEvent(new MyRecommendedCourseForm.SaveEvent(this));
    }

    public static abstract class MyRecCoursesFormEvent extends ComponentEvent<MyRecommendedCourseForm> {

        protected MyRecCoursesFormEvent(MyRecommendedCourseForm source) {
            super(source, false);
        }
    }

    public static class CloseEvent extends MyRecommendedCourseForm.MyRecCoursesFormEvent {
        CloseEvent(MyRecommendedCourseForm source) {
            super(source);
        }
    }

    public static class SaveEvent extends MyRecommendedCourseForm.MyRecCoursesFormEvent {
        protected SaveEvent(MyRecommendedCourseForm source) {
            super(source);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}