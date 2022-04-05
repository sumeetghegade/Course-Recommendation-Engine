package com.example.courserecommender.data.model;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Faculty {

    @Id
    private Long id;
    private String name;
    private String password;

    public Faculty() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
