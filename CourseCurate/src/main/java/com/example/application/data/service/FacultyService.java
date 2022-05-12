package com.example.application.data.service;

import com.example.application.data.model.Course;
import com.example.application.data.model.User;
import com.example.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FacultyService {


    @Autowired
    UserRepository userRepository;

    public List<User> findAllFaculty(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return userRepository.findAllByRole("Faculty");
        }
        else {
            List<User> list =  userRepository.findByNameContainingAndRole(filterText, "Faculty");
            return list;
        }
    }

    public int countStudents() {
        return findAllFaculty(null).size();
    }

    public void deleteContact(User user) {
        userRepository.delete(user);
    }

    public Set<Course> getAllCourses(Long id) {
        Optional<User> student = userRepository.findById(id);
        if(student.isEmpty())
            return new HashSet<>();
        else
            return student.get().getFacultyCourse();
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




    
}
