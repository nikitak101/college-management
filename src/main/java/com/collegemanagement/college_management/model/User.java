package com.collegemanagement.college_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)  // ✅ Ensure role is stored as ENUM in DB
    private Role role;

    @Column(name = "roll_no", unique = true, nullable = false)
    private String rollNumber;

    private String position;
    private String password;
}
