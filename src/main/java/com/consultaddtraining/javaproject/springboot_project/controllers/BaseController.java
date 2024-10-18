package com.consultaddtraining.javaproject.springboot_project.controllers;

import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import com.consultaddtraining.javaproject.springboot_project.dto.LoginResponse;
import com.consultaddtraining.javaproject.springboot_project.dto.UserRegisterDTO;
import com.consultaddtraining.javaproject.springboot_project.services.AuthService;
import com.consultaddtraining.javaproject.springboot_project.services.JWTService;
import com.consultaddtraining.javaproject.springboot_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaseController {

    @GetMapping(path = "/")
    public String getHome(){
        return "Welcome to home";
    }

    @GetMapping(path = "/secure")
    public String getSecure(Authentication authentication) {
        return "You have successfully authenticated,"+(authentication.getPrincipal());
    }
}