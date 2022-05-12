package com.example.application.views.admin;


import com.example.application.data.model.Course;
import com.example.application.data.model.User;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;


public class CourseForm extends FormLayout {
    Binder<Course> binder = new BeanValidationBinder<>(Course.class);
    TextField name = new TextField("Course Name");
    ComboBox<User> faculty = new ComboBox<>("Faculty");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private Course course;

    public CourseForm(List<User> users) {
        addClassName("course-form");
        faculty.setItems(users);
        faculty.setItemLabelGenerator(User::getName);
        binder.bindInstanceFields(this);
        add(name, faculty,
                createButtonsLayout());
    }

    public void setCourse(Course course) {
        this.course = course;
        binder.readBean(course);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, course)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(course);
            fireEvent(new SaveEvent(this, course));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class CourseFormEvent extends ComponentEvent<CourseForm> {
        private Course course;

        protected CourseFormEvent(CourseForm source, Course course) {
            super(source, false);
            this.course = course;
        }

        public Course getCourse() {
            return course;
        }
    }

    public static class SaveEvent extends CourseFormEvent {
        SaveEvent(CourseForm source, Course course) {
            super(source, course);
        }
    }

    public static class DeleteEvent extends CourseFormEvent {
        DeleteEvent(CourseForm source, Course course) {
            super(source, course);
        }

    }

    public static class CloseEvent extends CourseFormEvent {
        CloseEvent(CourseForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}