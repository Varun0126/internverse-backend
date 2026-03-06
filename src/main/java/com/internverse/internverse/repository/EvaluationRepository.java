package com.internverse.internverse.repository;

import com.internverse.internverse.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}