package com.consultaddtraining.javaproject.springboot_project.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "posts")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Builder    // when you need several custom constructors
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String body;
    private String tags;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToMany(mappedBy = "liked", fetch = FetchType.EAGER)     //like cant exist w/o user here
    private Set<UserEntity> likes;

    @Column(updatable = false)
    private LocalTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalTime.now();
    }

    public boolean addLike(UserEntity user){
        boolean added = likes.add(user);
        if (added) {
            user.getLiked().add(this); // Make sure to add the reverse relationship
        }
        return added;
    }

    public boolean removeLike(UserEntity user){
        return likes.remove(user);
    }
}

//   @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "artist_id")
//    private Artist artist;