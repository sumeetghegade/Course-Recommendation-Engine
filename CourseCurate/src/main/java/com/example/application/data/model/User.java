package com.example.application.data.model;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class User {

    @Relationship(type = "INTERESTED_IN", direction = Relationship.Direction.OUTGOING)
    Set<Domain> domain;
    @Relationship(type = "TAKEN", direction = Relationship.Direction.OUTGOING)
    Set<Course> course;
    @Relationship(type = "TEACHES", direction = Relationship.Direction.OUTGOING)
    Set<Course> facultyCourse;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String emailID;
    private String role;
    public User(Set<Domain> domain, Set<Course> course, Set<Course> facultyCourse, Long id, String name, String password, String emailID, String role) {
        this.domain = domain;
        this.course = course;
        this.facultyCourse = facultyCourse;
        this.id = id;
        this.name = name;
        this.password = password;
        this.emailID = emailID;
        this.role = role;
    }
    public User() {
    }

    public Set<Course> getFacultyCourse() {
        return facultyCourse;
    }

    public void setFacultyCourse(Set<Course> facultyCourse) {
        this.facultyCourse = facultyCourse;
    }

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }

    public Set<Domain> getDomain() {
        return domain;
    }

    public void setDomain(Set<Domain> domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", emailID='" + emailID + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
