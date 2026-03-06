package com.internverse.internverse.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String deadline;
    @ManyToOne
    @JoinColumn(name = "intern_id")
    private Intern intern;

}