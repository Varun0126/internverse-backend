package com.internverse.internverse.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Intern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String department;
    private String status;
}