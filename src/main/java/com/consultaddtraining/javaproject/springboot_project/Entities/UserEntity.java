package com.consultaddtraining.javaproject.springboot_project.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean isActive=true;
    @Column(updatable = false)
    private LocalTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalTime.now();
    }
}

//@OneToOne(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//private Ranking ranking;