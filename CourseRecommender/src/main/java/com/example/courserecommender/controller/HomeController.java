package com.example.courserecommender.controller;

import com.example.courserecommender.data.model.User;
import com.example.courserecommender.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class HomeController {

    @Autowired
    UserRepository repo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/home/{username}")
    public String home(@PathVariable(value = "username") String name) {

        User user = repo.findByName(name);

        if(user.getRole().equals("Student"))
            return "student";
        if(user.getRole().equals("Faculty"))
            return "faculty";
        if(user.getRole().equals("Student"))
            return "admin";

        return "default";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is admin page";
    }
}
