package com.internverse.internverse.service;

import com.internverse.internverse.model.Task;
import com.internverse.internverse.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    // Get all tasks (Admin + Intern)
    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    // Admin creates task
    public Task saveTask(Task task){
        return repository.save(task);
    }
}