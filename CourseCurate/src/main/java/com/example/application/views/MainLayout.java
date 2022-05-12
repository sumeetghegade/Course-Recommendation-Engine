package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.admin.CourseListView;
import com.example.application.views.admin.DomainListView;
import com.example.application.views.admin.FacultyListView;
import com.example.application.views.admin.StudentListView;
import com.example.application.views.faculty.FacultyCoursesListView;
import com.example.application.views.student.MyCoursesListView;
import com.example.application.views.student.MyDomainsListView;
import com.example.application.views.student.MyRecommendedCoursesListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.annotation.security.PermitAll;

@Route(value = "")
@PermitAll
public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        String username = securityService.getAuthenticatedUser().getUser().getName();
        String role = securityService.getAuthenticatedUser().getUser().getRole();
        Avatar av = new Avatar(username);
        H1 logo = new H1();
        if (role.equals("Faculty")) {
            logo.setText("Welcome, Prof. " + username);
        } else {
            logo.setText("Welcome, " + username);
        }
        logo.addClassNames("text-l", "m-m");
        Button logout = new Button("Log out", e -> securityService.logout());
        DrawerToggle dw = new DrawerToggle();
        HorizontalLayout header = new HorizontalLayout(
                dw,
                logo,
                logout

        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        System.out.println(securityService.getAuthenticatedUser().getAuthorities().toString());


        if (securityService.getAuthenticatedUser().getUser().getRole().equals("Admin")) {
            RouterLink listLink = new RouterLink("Course List", CourseListView.class);
            listLink.setHighlightCondition(HighlightConditions.sameLocation());

            RouterLink listLink2 = new RouterLink("Student List", StudentListView.class);
            listLink2.setHighlightCondition(HighlightConditions.sameLocation());

            RouterLink listLink3 = new RouterLink("Faculty List", FacultyListView.class);
            listLink3.setHighlightCondition(HighlightConditions.sameLocation());

            RouterLink listLink4 = new RouterLink("Domain List", DomainListView.class);
            listLink4.setHighlightCondition(HighlightConditions.sameLocation());

            addToDrawer(new VerticalLayout(
                    listLink, listLink2, listLink3, listLink4
            ));
        }

        if (securityService.getAuthenticatedUser().getUser().getRole().equals("Student")) {

            RouterLink listLink1 = new RouterLink("Favourite Domains", MyDomainsListView.class);
            listLink1.setHighlightCondition(HighlightConditions.sameLocation());

            RouterLink listLink2 = new RouterLink("All Courses", MyCoursesListView.class);
            listLink2.setHighlightCondition(HighlightConditions.sameLocation());

            RouterLink listLink3 = new RouterLink("Course Recommendations", MyRecommendedCoursesListView.class);
            listLink3.setHighlightCondition(HighlightConditions.sameLocation());

            addToDrawer(new VerticalLayout(
                    listLink1, listLink2, listLink3
            ));
        }

        if (securityService.getAuthenticatedUser().getUser().getRole().equals("Faculty")) {

            RouterLink listLink1 = new RouterLink("MyCourses", FacultyCoursesListView.class);
            listLink1.setHighlightCondition(HighlightConditions.sameLocation());


            addToDrawer(new VerticalLayout(
                    listLink1
            ));
        }

    }
}