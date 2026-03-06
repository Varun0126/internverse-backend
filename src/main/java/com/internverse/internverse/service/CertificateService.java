package com.internverse.internverse.service;

import com.internverse.internverse.model.Certificate;
import com.internverse.internverse.model.User;
import com.internverse.internverse.repository.CertificateRepository;
import com.internverse.internverse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Certificate generateCertificate(Certificate certificate) {

        // ✅ Validate intern exists by email
        User intern = userRepository.findByEmail(certificate.getInternEmail())
                .orElseThrow(() -> new RuntimeException(
                    "No intern found with email: " + certificate.getInternEmail()
                ));

        // ✅ Ensure user is an intern not admin
        if (!intern.getRole().equals("ROLE_INTERN")) {
            throw new RuntimeException(
                "User " + certificate.getInternEmail() + " is not an intern"
            );
        }

        // ✅ Build a fresh certificate object with name from DB
        // Do NOT rely on the incoming object's internName — it may be null
        Certificate newCert = new Certificate();
        newCert.setInternName(intern.getName());           // from DB — never null
        newCert.setInternEmail(certificate.getInternEmail());
        newCert.setCourse(certificate.getCourse());
        newCert.setCompletionDate(certificate.getCompletionDate());

        System.out.println("Issuing cert to: " + intern.getName() + " <" + intern.getEmail() + ">");

        return repository.save(newCert);
    }

    public List<Certificate> getCertificatesByEmail(String internEmail) {
        return repository.findByInternEmail(internEmail);
    }

    public List<Certificate> getAllCertificates() {
        return repository.findAll();
    }
}