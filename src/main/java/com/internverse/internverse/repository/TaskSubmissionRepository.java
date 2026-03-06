package com.internverse.internverse.repository;

import com.internverse.internverse.model.TaskSubmission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskSubmissionRepository extends JpaRepository<TaskSubmission, Long> {

    List<TaskSubmission> findByIntern_Email(String email);

    // ✅ Fetch submissions with evaluation data eagerly loaded
    @Query("SELECT s FROM TaskSubmission s LEFT JOIN FETCH s.task WHERE s.intern.email = :email")
    List<TaskSubmission> findByInternEmailWithTask(@Param("email") String email);
}