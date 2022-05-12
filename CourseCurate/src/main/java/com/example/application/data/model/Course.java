package com.example.application.data.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Relationship(type = "TEACHES", direction = Relationship.Direction.INCOMING)
    private User faculty;

    public Course(Long id, String name, User faculty) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
    }

    public User getFaculty() {
        return faculty;
    }

    public void setFaculty(User faculty) {
        this.faculty = faculty;
    }

    public Course() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
