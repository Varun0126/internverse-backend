package com.internverse.internverse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;  // ✅ Integer not int — fixes JSON deserialization

    private String feedback;

    private String evaluatedDate;

    @OneToOne
    @JoinColumn(name = "submission_id")
    @JsonIgnoreProperties({"evaluation", "intern"})
    private TaskSubmission submission;
}
