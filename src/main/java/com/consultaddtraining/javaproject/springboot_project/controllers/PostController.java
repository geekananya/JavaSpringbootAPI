package com.consultaddtraining.javaproject.springboot_project.controllers;

import com.consultaddtraining.javaproject.springboot_project.dto.PostDTO;
import com.consultaddtraining.javaproject.springboot_project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RestController
@RequestMapping(path = "/posts")
public class PostController {

    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping(path = "/get")
    public List<PostDTO> getPosts(){
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{id}")
    public PostDTO getPost(@PathVariable("id") Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping(path = "/create")
    public PostDTO createPost(PostDTO request, Authentication authentication){
        return postService.createNewPost(request, authentication.getName());
    }

    @DeleteMapping(path = "/delete/{id}")
    public Long deletePost(@PathVariable("id") Long postId){
        return postService.deletePost(postId);
    }

    @PutMapping(path = "/update/{id}")
    public PostDTO UpdatePost(@PathVariable("id") Long postId, PostDTO request){
        return postService.updatePost(postId, request);
    }

    @PutMapping(path = "/update/like/{id}")
    public PostDTO LikePost(@PathVariable("id") Long postId, Authentication authentication){
//        System.out.println(authentication.);
        return postService.likePost(authentication.getName(), postId);
    }

}
