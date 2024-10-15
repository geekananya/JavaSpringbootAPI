package com.consultaddtraining.javaproject.springboot_project.controllers;

import com.consultaddtraining.javaproject.springboot_project.dto.PostDTO;
import com.consultaddtraining.javaproject.springboot_project.dto.UserDTO;
import com.consultaddtraining.javaproject.springboot_project.dto.UserRegisterDTO;
import com.consultaddtraining.javaproject.springboot_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@ControllerAdvice
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    @PostMapping(path = "/register")
    public UserDTO registerUser(UserRegisterDTO request){
        return userService.createNewUser(request);
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
