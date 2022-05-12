package com.example.application.views.student;

import com.example.application.data.model.Course;
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

import java.util.List;
import java.util.Set;

public class MyCoursesForm extends FormLayout {

    MultiSelectListBox<Course> course = new MultiSelectListBox<>();


    Button add = new Button("Add");
    Button cancel = new Button("Cancel");

    private StudentService studentService;
    private SecurityService securityService;


    public MyCoursesForm(List<Course> courses, StudentService studentService, SecurityService securityService) {

        this.studentService = studentService;
        this.securityService = securityService;
        course.setItems(courses);
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
        cancel.addClickListener(event -> fireEvent(new MyCoursesForm.CloseEvent(this)));

        add.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(add, cancel);
    }

    private void validateAndAdd(MyCoursesForm form) {
        Set<Course> selectedCourses = form.course.getSelectedItems();
        studentService.saveAllCourses(form.securityService.getAuthenticatedUser().getUser().getId(), selectedCourses);
        fireEvent(new MyCoursesForm.SaveEvent(this));
    }

    public static abstract class MyCoursesFormEvent extends ComponentEvent<MyCoursesForm> {

        protected MyCoursesFormEvent(MyCoursesForm source) {
            super(source, false);
        }
    }

    public static class CloseEvent extends MyCoursesForm.MyCoursesFormEvent {
        CloseEvent(MyCoursesForm source) {
            super(source);
        }
    }

    public static class SaveEvent extends MyCoursesForm.MyCoursesFormEvent {
        protected SaveEvent(MyCoursesForm source) {
            super(source);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}