package com.internverse.internverse.controller;

import com.internverse.internverse.model.Evaluation;
import com.internverse.internverse.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService service;

    @PostMapping
    public Evaluation evaluate(@RequestBody Evaluation evaluation){
        return service.evaluate(evaluation);
    }
}