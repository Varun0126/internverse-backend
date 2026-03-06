package com.internverse.internverse.service;

import com.internverse.internverse.model.TaskSubmission;
import com.internverse.internverse.model.User;
import com.internverse.internverse.repository.TaskSubmissionRepository;
import com.internverse.internverse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskSubmissionService {

    @Autowired
    private TaskSubmissionRepository repository;

    @Autowired
    private UserRepository userRepository;

    // Intern submits task
    public TaskSubmission submitTask(TaskSubmission submission, String email){

        submission.setSubmissionDate(LocalDate.now().toString());
        submission.setStatus("PENDING");

        // find intern from JWT email
        User intern = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        submission.setIntern(intern);

        return repository.save(submission);
    }

    public List<TaskSubmission> getSubmissionsByEmail(String email){

        return repository.findByIntern_Email(email);
    }

    public List<TaskSubmission> getAllSubmissions(){

        return repository.findAll();
    }
}