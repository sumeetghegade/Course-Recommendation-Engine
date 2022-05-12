package com.example.application.data.repository;

import com.example.application.data.model.Course;
import com.example.application.data.model.Domain;
import com.example.application.data.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (s:Student) RETURN s")
    Collection<User> getAllUsers();

    User findByName(String name);
    User findById(String id);
    List<User> findAllByRole(String role);
    List<User> findByNameContainingAndRole(String name, String role);
    User findByEmailID(String emailid);




    @Query("MATCH (d:Domain {name:$domain})<-[:INTERESTED_IN]-(:User)-[:TAKEN]->(c:Course) WITH c, COUNT(c) as cnt WHERE cnt > 1 RETURN c.name")
    Set<String> getRecommendations(@Param("domain") String domain);


}
