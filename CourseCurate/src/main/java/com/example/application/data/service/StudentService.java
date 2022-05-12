package com.example.application.data.service;


import com.example.application.data.model.Course;
import com.example.application.data.model.Domain;
import com.example.application.data.model.User;
import com.example.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllStudents(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return userRepository.findAllByRole("Student");
        }
        else {
            List<User> list =  userRepository.findByNameContainingAndRole(filterText, "Student");
            return list;
        }
    }

    public int countStudents() {
        return findAllStudents(null).size();
    }

    public void deleteContact(User user) {
        userRepository.delete(user);
    }

    public void saveUser(User user) {
        if(user == null) {
            System.err.println("User is null");
            return;
        }
        else {
            userRepository.save(user);
        }
    }

    public Set<Domain> getAllDomains(Long id) {
        Optional<User> student = userRepository.findById(id);
        if(student.isEmpty())
            return new HashSet<>();
        else
            return student.get().getDomain();
    }

    public void saveAllDomains(Long id, Set<Domain> domains) {
        Optional<User> studentOp = userRepository.findById(id);
        if(studentOp.isEmpty()) {
            System.err.println("User is null");
            return;
        }
        else {
            User student = studentOp.get();
            student.setDomain(domains);
            userRepository.save(student);
        }
    }

    public Set<Course> getAllCourses(Long id) {
        Optional<User> student = userRepository.findById(id);
        if(student.isEmpty())
            return new HashSet<>();
        else
            return student.get().getCourse();
    }

    public void saveAllCourses(Long id, Set<Course> courses) {
        Optional<User> studentOp = userRepository.findById(id);
        if(studentOp.isEmpty()) {
            System.err.println("User is null");
            return;
        }
        else {
            User student = studentOp.get();
            student.setCourse(courses);
            userRepository.save(student);
        }
    }

    public Set<String> getRecommendations(Set<Domain> domains) {

        Set<String> result = new HashSet<>();
        if(domains.size() == 0)
            return  result;
        for(Domain d: domains) {
            result.addAll(userRepository.getRecommendations(d.getName()));
        }
        return result;
    }


}
