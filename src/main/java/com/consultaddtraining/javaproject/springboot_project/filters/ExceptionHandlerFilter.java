package com.consultaddtraining.javaproject.springboot_project.filters;

import com.consultaddtraining.javaproject.springboot_project.dto.ErrorDTO;
import com.consultaddtraining.javaproject.springboot_project.exceptions.NotAuthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Autowired
    private final ObjectMapper objectMapper;

    public ExceptionHandlerFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (NotAuthorizedException e) {


            response.setContentType("application/json");

            ErrorDTO errorDTO = new ErrorDTO(e.getMessage(), 403, false);

            String jsonResponse = objectMapper.writeValueAsString(errorDTO);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
        }
    }
}



//.and().exceptionHandler().