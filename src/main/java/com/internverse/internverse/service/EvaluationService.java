package com.internverse.internverse.service;

import com.internverse.internverse.model.Evaluation;
import com.internverse.internverse.model.TaskSubmission;
import com.internverse.internverse.repository.EvaluationRepository;
import com.internverse.internverse.repository.TaskSubmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository repository;

    @Autowired
    private TaskSubmissionRepository submissionRepository;

    public Evaluation evaluate(Evaluation evaluation) {

        evaluation.setEvaluatedDate(LocalDate.now().toString());

        // ✅ Fetch the FULL submission from DB by ID
        // The incoming evaluation.getSubmission() only has {id}, so task/intern fields are null
        // Saving that partial object would wipe out task_id and intern_id in DB
        Long submissionId = evaluation.getSubmission().getId();

        TaskSubmission fullSubmission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found: " + submissionId));

        // ✅ Update only the status on the FULL object
        fullSubmission.setStatus("APPROVED");
        submissionRepository.save(fullSubmission);

        // ✅ Link the full submission to the evaluation
        evaluation.setSubmission(fullSubmission);

        return repository.save(evaluation);
    }
}