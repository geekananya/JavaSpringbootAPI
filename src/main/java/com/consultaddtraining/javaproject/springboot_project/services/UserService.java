package com.consultaddtraining.javaproject.springboot_project.services;

import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import com.consultaddtraining.javaproject.springboot_project.dto.UserDTO;
import com.consultaddtraining.javaproject.springboot_project.dto.UserRegisterDTO;
import com.consultaddtraining.javaproject.springboot_project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //dependency injection
    @Autowired
    final UserRepository userRepository;
    final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers(){
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> resp = new ArrayList<>();
        for (UserEntity user : users) {
            UserDTO dto = modelMapper.map(user, UserDTO.class);
            resp.add(dto);
        }
        return resp;
    }

    public UserDTO getUserById(Long id){
        Optional<UserEntity> entity = userRepository.findById(id);
        if(entity.isPresent()) {
            UserEntity user = entity.get();
            return modelMapper.map(user, UserDTO.class);
//            return new UserDTO(user.getId(), user.getName(), user.getEmail());
        }
        return new UserDTO();
    }

    public Long deleteUser(Long id){
        try {
            userRepository.deleteById(id);
            System.out.println("Deleted "+ id);
            return id;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1L;
//        return null;
    }

    public UserDTO updateUser(Long id, UserDTO request){
        try {
            UserEntity user = userRepository.getReferenceById(id);
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            userRepository.save(user);
            UserDTO resp = modelMapper.map(user, UserDTO.class);
            System.out.println("Updated "+ user.getId());
            return resp;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new UserDTO();
//        return null;
    }
}
