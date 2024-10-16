package com.consultaddtraining.javaproject.springboot_project.controllers;

import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import com.consultaddtraining.javaproject.springboot_project.dto.*;
import com.consultaddtraining.javaproject.springboot_project.services.AuthService;
import com.consultaddtraining.javaproject.springboot_project.services.JWTService;
import com.consultaddtraining.javaproject.springboot_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@ControllerAdvice
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;    //to access this dependency we make a bean

    @Autowired
    private final UserService userService;
    private final AuthService authService;
    private final JWTService jwtService;

    public UserController(UserService userService, AuthService authService, JWTService jwtService) {
        this.userService = userService;
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/register")
    public LoginResponse registerUser(UserRegisterDTO request){
        UserEntity user = authService.signup(request);
        String jwt = jwtService.generateToken(user);

        return new LoginResponse(jwt, jwtService.getExpirationTime());
    }

    @PostMapping(path = "/login")
    public LoginResponse login(UserLoginDTO request){
        UserEntity user = authService.authenticate(request);

        String jwt = jwtService.generateToken(user);

        return new LoginResponse(jwt, jwtService.getExpirationTime());
    }

    @GetMapping(path = "/")
    public List<UserDTO> getUsers() {
        System.out.println("All users");
        return userService.getAllUsers();
        // get all users
    }

    @GetMapping(path = "/{id}")
    public UserDTO getUser(@PathVariable("id") Long empId){
        return userService.getUserById(empId);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Long deleteUser(@PathVariable("id") Long postId){
        return userService.deleteUser(postId);
    }

    @PutMapping(path = "/update/{id}")
    public UserDTO UpdatePost(@PathVariable("id") Long userId, UserDTO request){
        return userService.updateUser(userId, request);
    }
}
