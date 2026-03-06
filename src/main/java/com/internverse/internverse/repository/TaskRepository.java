package com.internverse.internverse.repository;

import com.internverse.internverse.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}