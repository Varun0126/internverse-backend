package com.internverse.internverse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internverse.internverse.model.Intern;
import com.internverse.internverse.service.InternService;

@RestController
@RequestMapping("/interns")

public class InternController {

    @Autowired
    private InternService service;

    @GetMapping
    public List<Intern> getInterns(){
        return service.getAllInterns();
    }

    @PostMapping
    public Intern addIntern(@RequestBody Intern intern){
        return service.saveIntern(intern);
    }

    @DeleteMapping("/{id}")
    public void deleteIntern(@PathVariable Long id){
        service.deleteIntern(id);
    }
}