package com.example.application.views.student;


import com.example.application.data.model.Domain;
import com.example.application.data.service.StudentService;
import com.example.application.security.SecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;


public class MyDomainsForm extends FormLayout {

    MultiSelectListBox<Domain> domain = new MultiSelectListBox<>();


    Button add = new Button("Add");
    Button cancel = new Button("Cancel");

    private StudentService studentService;
    private SecurityService securityService;


    public MyDomainsForm(List<Domain> domains, StudentService studentService, SecurityService securityService) {

        this.studentService = studentService;
        this.securityService = securityService;
        domain.setItems(domains);
        domain.setItemLabelGenerator(Domain::getName);
        add(
                domain,
                createButtonLayout()
        );
    }

    private Component createButtonLayout() {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        add.addClickListener(event -> validateAndAdd(this));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        add.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(add, cancel);
    }

    private void validateAndAdd(MyDomainsForm form) {
        Set<Domain> selectedDomains = form.domain.getSelectedItems();
        studentService.saveAllDomains(form.securityService.getAuthenticatedUser().getUser().getId(), selectedDomains);
        fireEvent(new SaveEvent(this));
    }

    public static abstract class MyDomainsFormEvent extends ComponentEvent<MyDomainsForm> {

        protected MyDomainsFormEvent(MyDomainsForm source) {
            super(source, false);
        }
    }

    public static class CloseEvent extends MyDomainsFormEvent {
        CloseEvent(MyDomainsForm source) {
            super(source);
        }
    }

    public static class SaveEvent extends MyDomainsFormEvent {
        protected SaveEvent(MyDomainsForm source) {
            super(source);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}