package com.example.application.data.repository;

import com.example.application.data.model.Course;
import com.example.application.data.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends Neo4jRepository<Course, Long> {

    public List<Course> findByNameContaining(String filterText);

    public Course findByName(String name);

}
