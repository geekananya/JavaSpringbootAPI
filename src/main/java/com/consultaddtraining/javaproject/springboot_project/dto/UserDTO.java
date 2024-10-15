package com.consultaddtraining.javaproject.springboot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;     // generates getters and setters
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
}
