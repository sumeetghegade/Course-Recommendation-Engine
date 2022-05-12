package com.example.application.data.repository;

import com.example.application.data.model.Domain;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomainRepository extends Neo4jRepository<Domain, Long> {

    List<Domain> findAll();
    List<Domain> findAllByName(String name);

}
