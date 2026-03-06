package com.internverse.internverse.controller;

import com.internverse.internverse.model.TaskSubmission;
import com.internverse.internverse.service.TaskSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")

public class TaskSubmissionController {

    @Autowired
    private TaskSubmissionService service;

    // Intern submits task
    @PostMapping
    public TaskSubmission submitTask(
        @RequestBody TaskSubmission submission,
        Authentication authentication){

        String email = authentication.getName();
            System.out.println("JWT USER EMAIL: " + authentication.getName());  
        return service.submitTask(submission, email);
    }

    // Intern sees their submissions
    @GetMapping("/my")
    public List<TaskSubmission> mySubmissions(Authentication authentication){

        String email = authentication.getName();

        return service.getSubmissionsByEmail(email);
    }

    // Admin can see all submissions
    @GetMapping
    public List<TaskSubmission> getAllSubmissions(){
        return service.getAllSubmissions();
    }
}