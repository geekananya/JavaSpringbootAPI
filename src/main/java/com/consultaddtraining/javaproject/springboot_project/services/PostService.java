package com.consultaddtraining.javaproject.springboot_project.services;

import com.consultaddtraining.javaproject.springboot_project.Entities.PostEntity;
import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import com.consultaddtraining.javaproject.springboot_project.dto.PostDTO;
import com.consultaddtraining.javaproject.springboot_project.exceptions.ResourceNotFoundException;
import com.consultaddtraining.javaproject.springboot_project.repositories.PostRepository;
import com.consultaddtraining.javaproject.springboot_project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    //dependency injection
    @Autowired
    final PostRepository postRepository;
    final UserRepository userRepository;
    final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
        PostEntity post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found"));
//        if(entity.isPresent()) {
//            PostEntity post = entity.get();
            return modelMapper.map(post, PostDTO.class);
//        }
    }

    public PostDTO createNewPost(PostDTO request, String useremail){
        PostEntity post = modelMapper.map(request, PostEntity.class);
        post.setAuthor(userRepository.findByEmail(useremail)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));
        PostEntity saved = postRepository.save(post);
        System.out.println(saved);
        PostDTO resp = modelMapper.map(saved, PostDTO.class);
        System.out.println("Post created successfully");
        return resp;
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

    @Transactional
    public PostDTO likePost(String useremail, Long id){
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        UserEntity user = userRepository.findByEmail(useremail).orElseThrow(()-> new ResourceNotFoundException("Authenticated User not found"));
        boolean l = post.addLike(user);

        if(l) {
            PostEntity saved = postRepository.save(post);
            PostDTO resp = modelMapper.map(saved, PostDTO.class);
            return resp;
        }else{
            return modelMapper.map(post, PostDTO.class);
        }

    }
}
