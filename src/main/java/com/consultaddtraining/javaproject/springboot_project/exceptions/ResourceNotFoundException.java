package com.consultaddtraining.javaproject.springboot_project.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private String message;

    @Override
    public String getMessage(){
        return this.message;
    }
}
