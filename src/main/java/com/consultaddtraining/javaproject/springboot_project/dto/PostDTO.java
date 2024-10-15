package com.consultaddtraining.javaproject.springboot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;     // generates getters and setters
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private Long authorId;
    private String body;
    private String tags= "";
    private int likes_no=0;
}
