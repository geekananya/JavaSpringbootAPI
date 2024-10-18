package com.consultaddtraining.javaproject.springboot_project.dto;

import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import lombok.*;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private Long authorId = getAuthorId();
    private String body;
    private String tags= "";
//    private Set<Long> likes = new HashSet<>();
//
//    public void setLikesFromUserEntities(Set<UserEntity> userEntities) {
//        this.likes = userEntities.stream()
//                .map(UserEntity::getId) // Extract IDs
//                .collect(Collectors.toSet());
//    }

}
