package com.internverse.internverse.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String internName;   // for display on certificate

    @Column(unique = false)
    private String internEmail;  // ✅ unique identifier to avoid name conflicts

    private String course;

    private String completionDate;

}