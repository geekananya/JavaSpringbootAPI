package com.consultaddtraining.javaproject.springboot_project.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;


@Getter
@Setter
public class NotAuthorizedException extends AuthenticationException {
    private String message;

    public NotAuthorizedException(String message){
        super(message);
        this.message = message;
    }

//    @Override
//    public String getMessage(){
//        return this.message;
//    }
}
