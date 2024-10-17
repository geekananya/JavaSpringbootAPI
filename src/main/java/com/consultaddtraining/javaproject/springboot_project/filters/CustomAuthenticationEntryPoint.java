package com.consultaddtraining.javaproject.springboot_project.filters;

import com.consultaddtraining.javaproject.springboot_project.dto.ErrorDTO;
import com.consultaddtraining.javaproject.springboot_project.exceptions.NotAuthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // Set the response status
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // Create an ErrorDTO instance using NotAuthorizedException
        System.out.println("auth except"+
                authException.getMessage());
//        NotAuthorizedException notAuthorizedException = new NotAuthorizedException("Unauthorized access");
        ErrorDTO errorDTO;
//        if (authException instanceof NotAuthorizedException) {
//            NotAuthorizedException notAuthEx = (NotAuthorizedException) authException;
//            System.out.println("it is");
//            errorDTO = new ErrorDTO(notAuthEx.getMessage(), 401, true);
//        }else
            errorDTO = new ErrorDTO(authException.getMessage(), 401, true);

        // Convert ErrorDTO to JSON
        String jsonResponse = objectMapper.writeValueAsString(errorDTO);
        System.out.println("errorDTO : "+errorDTO);

//        response.setCharacterEncoding("UTF-8");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Write the response
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
}