package com.example.application.data.service;


import com.example.application.data.model.Domain;
import com.example.application.data.model.User;
import com.example.application.data.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {

    @Autowired
    DomainRepository domainRepository;

    public List<Domain> findAllDomains(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return domainRepository.findAll();
        }
        else {
            List<Domain> list =  domainRepository.findAllByName(filterText);
            return list;
        }
    }

    public void deleteDomain(Domain domain) {
        domainRepository.delete(domain);
    }

    public void saveDomain(Domain domain) {
        if(domain == null) {
            System.err.println("domain is null");
            return;
        }
        else {
            domainRepository.save(domain);
        }
    }
}
