package com.internverse.internverse.repository;

import com.internverse.internverse.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternRepository extends JpaRepository<Intern, Long> {

}