package com.example.application.data.model;



import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Node
public class Domain {

    @Id @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Domain(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

    public Domain() {

    }

    public String getName() {
        return name;
    }
}
