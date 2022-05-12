package com.example.application.views.admin;

import com.example.application.data.model.Domain;
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

public class DomainForm extends FormLayout {

    Binder<Domain> binder = new BeanValidationBinder<>(Domain.class);
    TextField name = new TextField("Name");



    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private Domain domain;

    public DomainForm() {
        addClassName("domain-form");

        binder.bindInstanceFields(this);
        add(name,
                createButtonsLayout());
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
        binder.readBean(domain);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DomainForm.DeleteEvent(this, domain)));
        close.addClickListener(event -> fireEvent(new DomainForm.CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(domain);
            fireEvent(new DomainForm.SaveEvent(this, domain));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class DomainFormEvent extends ComponentEvent<DomainForm> {
        private Domain domain;

        protected DomainFormEvent(DomainForm source, Domain domain) {
            super(source, false);
            this.domain = domain;
        }

        public Domain getDomain() {
            return domain;
        }
    }

    public static class SaveEvent extends DomainForm.DomainFormEvent {
        SaveEvent(DomainForm source, Domain domain) {
            super(source, domain);
        }
    }

    public static class DeleteEvent extends DomainForm.DomainFormEvent {
        DeleteEvent(DomainForm source, Domain domain) {
            super(source, domain);
        }

    }

    public static class CloseEvent extends DomainForm.DomainFormEvent {
        CloseEvent(DomainForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
