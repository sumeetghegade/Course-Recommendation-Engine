package com.example.courserecommender.data.service;



import com.example.courserecommender.data.model.User;
import com.example.courserecommender.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    UserRepository studentRepo;

    public Collection<User> getAll() {
        return studentRepo.getAllUsers();
    }
}
