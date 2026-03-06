package com.internverse.internverse.controller;

import com.internverse.internverse.model.Task;
import com.internverse.internverse.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")

public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public List<Task> getTasks(){
        return service.getAllTasks();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task){
        return service.saveTask(task);
    }
}