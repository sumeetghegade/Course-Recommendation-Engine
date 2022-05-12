package com.example.application.views.admin;

import com.example.application.data.model.User;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class StudentForm extends FormLayout {

    Binder<User> binder = new BeanValidationBinder<>(User.class);
    TextField name = new TextField("Name");
    TextField password = new TextField("Password");
    TextField emailID = new TextField("Email ID");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private User user;

    public StudentForm() {
        addClassName("student-form");

        binder.bindInstanceFields(this);
        add(name, password, emailID,
                createButtonsLayout());
    }

    public void setStudent(User user) {
        this.user = user;
        binder.readBean(user);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new StudentForm.DeleteEvent(this, user)));
        close.addClickListener(event -> fireEvent(new StudentForm.CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(user);
            fireEvent(new StudentForm.SaveEvent(this, user));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class StudentFormEvent extends ComponentEvent<StudentForm> {
        private User user;

        protected StudentFormEvent(StudentForm source, User user) {
            super(source, false);
            if(user != null)
                user.setRole("Student");
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    public static class SaveEvent extends StudentForm.StudentFormEvent {
        SaveEvent(StudentForm source, User user) {
            super(source, user);
        }
    }

    public static class DeleteEvent extends StudentForm.StudentFormEvent {
        DeleteEvent(StudentForm source, User user) {
            super(source, user);
        }

    }

    public static class CloseEvent extends StudentForm.StudentFormEvent {
        CloseEvent(StudentForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
