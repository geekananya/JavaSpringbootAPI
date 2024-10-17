package com.consultaddtraining.javaproject.springboot_project.exceptions;

import com.consultaddtraining.javaproject.springboot_project.dto.ErrorDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDTO resourceNotFoundExceptionHandler(ResourceNotFoundException rnfe){
        System.out.println(rnfe.getMessage());
        return new ErrorDTO(rnfe.getMessage(), 404, false);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ErrorDTO notAuthorisedException(NotAuthorizedException e){
        System.out.println(e.getMessage());
        return new ErrorDTO(e.getMessage(), 403, false);
    }
}
