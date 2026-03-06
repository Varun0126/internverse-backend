package com.internverse.internverse.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
public class TaskSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "github_link")
    private String githubLink;

    private String comment;

    @Column(name = "submission_date")
    private String submissionDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password"})  // ✅ don't expose password in response
    private User intern;

    // ✅ Include evaluation in submission response so intern can see rating/feedback
    @OneToOne(mappedBy = "submission", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"submission"})
    private Evaluation evaluation;
}