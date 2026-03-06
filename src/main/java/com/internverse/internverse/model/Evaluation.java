package com.internverse.internverse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    private String feedback;

    private String evaluatedDate;

    @OneToOne
    @JoinColumn(name = "submission_id")
    @JsonIgnoreProperties({"evaluation", "intern"})  // ✅ stop circular loop
    private TaskSubmission submission;
}