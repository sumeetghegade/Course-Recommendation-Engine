package com.example.application.data.service;



import com.example.application.data.model.Course;
import com.example.application.data.model.User;
import com.example.application.data.repository.CourseRepository;
import com.example.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    public List<Course> findAllCourses(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return courseRepository.findAll();
        }
        else {
            List<Course> list =  courseRepository.findByNameContaining(filterText);
            return list;
        }
    }

    public Long countCourses() {
        return courseRepository.count();
    }

    public void deleteContact(Course course) {
        courseRepository.delete(course);
    }

    public void saveCourse(Course course) {
        if(course == null) {
            System.err.println("Course is null");
            return;
        }
        else {
            courseRepository.save(course);
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAllByRole("Faculty");
    }

    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }


}
