package com.consultaddtraining.javaproject.springboot_project.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder    // when you need several custom constructors
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long authorId;
    private String body;
    private String tags;
    private int likes_no;

    @Column(updatable = false)
    private LocalTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalTime.now();
    }
}

//   @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "artist_id")
//    private Artist artist;