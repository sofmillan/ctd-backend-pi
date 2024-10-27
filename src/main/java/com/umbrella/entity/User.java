package com.umbrella.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "password_hash", length = 255)
    private String passwordHash;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
