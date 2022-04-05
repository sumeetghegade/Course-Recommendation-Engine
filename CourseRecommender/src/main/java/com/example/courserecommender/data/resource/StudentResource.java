package com.example.courserecommender.data.resource;


import com.example.courserecommender.data.model.User;
import com.example.courserecommender.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j/student")
public class StudentResource {

    @Autowired
    StudentService studentService;

    @GetMapping
    public Collection<User> getAll() {
        return studentService.getAll();
    }

}
