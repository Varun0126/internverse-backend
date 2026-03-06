package com.internverse.internverse.repository;

import com.internverse.internverse.model.Certificate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByInternEmail(String internEmail); // ✅ query by email
}