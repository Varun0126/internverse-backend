package com.internverse.internverse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internverse.internverse.model.Intern;
import com.internverse.internverse.repository.InternRepository;

@Service
public class InternService {

    @Autowired
    private InternRepository repository;

    public List<Intern> getAllInterns(){
        return repository.findAll();
    }

    public Intern saveIntern(Intern intern){
        return repository.save(intern);
    }

    public void deleteIntern(Long id){
        repository.deleteById(id);
    }
}