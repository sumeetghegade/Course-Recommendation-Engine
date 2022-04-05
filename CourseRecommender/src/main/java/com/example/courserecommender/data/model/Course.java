package com.example.courserecommender.data.model;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Course {

    @Id
    private Long course_id;
    private String course_name;

    public Course() {

    }

    public Long getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }
}
