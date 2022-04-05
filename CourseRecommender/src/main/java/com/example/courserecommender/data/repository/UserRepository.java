package com.example.courserecommender.data.repository;

import com.example.courserecommender.data.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (s:Student) RETURN s")
    Collection<User> getAllUsers();

    User findByName(String name);

}
