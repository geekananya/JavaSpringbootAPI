package com.consultaddtraining.javaproject.springboot_project.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
//@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;
    private boolean isActive=true;

    @Column(updatable = false)
    private LocalTime createdAt;


    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)   // dont display all posts by students unless needed or asked for(User.getPosts())
    private List<PostEntity> posts;

    // doesnt create a new column, just defines and creates a relation(table) to manage the many-to-many association.
    @ManyToMany
    @JoinTable(name = "user_liked",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"))
    private Set<PostEntity> liked;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    //returns the user’s roles list to manage perms
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

//@OneToOne(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//private Ranking ranking;