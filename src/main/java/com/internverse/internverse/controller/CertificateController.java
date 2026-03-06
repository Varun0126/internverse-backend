package com.internverse.internverse.controller;

import com.internverse.internverse.model.Certificate;
import com.internverse.internverse.service.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService service;

    // Admin issues certificate
    @PostMapping
    public Certificate generateCertificate(@RequestBody Certificate certificate) {
        return service.generateCertificate(certificate);
    }

    // Admin views all certificates
    @GetMapping
    public List<Certificate> getAllCertificates() {
        return service.getAllCertificates();
    }

    // ✅ Intern fetches their own certificates by email
    @GetMapping("/intern/{email}")
    public List<Certificate> getCertificatesByEmail(@PathVariable String email) {
        return service.getCertificatesByEmail(email);
    }
}