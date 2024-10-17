package com.consultaddtraining.javaproject.springboot_project.exceptions;

import com.consultaddtraining.javaproject.springboot_project.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//@RestController
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
//
//    @RequestMapping("/error")
//    public ErrorDTO handleError(final HttpServletRequest request,
//                                      final HttpServletResponse response) {
//
//        Object exception = request.getAttribute("javax.servlet.error.exception");
//
//        // TODO: Logic to inspect exception thrown from Filters...
//        System.out.println(exception.toString());
////        return ResponseEntity.badRequest().body(new Error(/* whatever */));
//        return new ErrorDTO();
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//



}
