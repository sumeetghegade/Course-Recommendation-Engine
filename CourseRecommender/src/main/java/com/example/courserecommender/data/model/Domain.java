package com.example.courserecommender.data.model;



import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Node
public class Domain {

    @Id
    private String name;
    private List<String> topicsCovered;

    public Domain(List<String> topicsCovered) {

    }

    public List<String> getTopicsCovered() {
        return topicsCovered;
    }

    public String getName() {
        return name;
    }
}
