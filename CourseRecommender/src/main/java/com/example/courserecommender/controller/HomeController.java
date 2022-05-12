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

    // https://stackoverflow.com/questions/38221461/how-to-make-an-html-file-appear-on-localhost-with-spring-boot
    @GetMapping("/")
    public String homePage() {
    	return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/signup")
	public String signup() {
		return "signup";
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
