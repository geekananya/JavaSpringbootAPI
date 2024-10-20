package com.consultaddtraining.javaproject.springboot_project.dto;

import com.consultaddtraining.javaproject.springboot_project.Entities.PostEntity;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private List<PostDTO> posts;
}
