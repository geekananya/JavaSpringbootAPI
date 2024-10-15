package com.consultaddtraining.javaproject.springboot_project.services;

import com.consultaddtraining.javaproject.springboot_project.Entities.PostEntity;
import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import com.consultaddtraining.javaproject.springboot_project.dto.PostDTO;
import com.consultaddtraining.javaproject.springboot_project.dto.UserDTO;
import com.consultaddtraining.javaproject.springboot_project.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    //dependency injection
    @Autowired
    final PostRepository postRepository;
    final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public PostService() {

    }

    public List<PostDTO> getAllPosts(){
        List<PostEntity> posts = postRepository.findAll();
        List<PostDTO> resp = new ArrayList<>();
        for (PostEntity post : posts) {
            PostDTO dto = modelMapper.map(post, PostDTO.class);
            resp.add(dto);
        }
        return resp;
    }

    public PostDTO getPostById(Long id){
        Optional<PostEntity> entity = postRepository.findById(id);
        if(entity.isPresent()) {
            PostEntity post = entity.get();
            return modelMapper.map(post, PostDTO.class);
        }
        return new PostDTO();
    }

    public PostDTO createNewPost(PostDTO request){
        try {
            PostEntity post = modelMapper.map(request, PostEntity.class);
            PostEntity saved = postRepository.save(post);
            System.out.println(saved);
            PostDTO resp = modelMapper.map(saved, PostDTO.class);
            System.out.println("Post created successfully");
            return resp;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new PostDTO();
//        return null;
    }

    public Long deletePost(Long id){
        try {
            postRepository.deleteById(id);
            System.out.println("Deleted "+ id);
            return id;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1L;
//        return null;
    }

    public PostDTO updatePost(Long id, PostDTO request){
        try {
            PostEntity post = postRepository.getReferenceById(id);
            post.setBody(request.getBody());
            post.setTags(request.getTags());
            postRepository.save(post);
            PostDTO resp = modelMapper.map(post, PostDTO.class);
            System.out.println("Updated "+ post.getId());
            return resp;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new PostDTO();
//        return null;
    }
}
