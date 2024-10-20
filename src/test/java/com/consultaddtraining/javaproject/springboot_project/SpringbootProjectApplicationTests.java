package com.consultaddtraining.javaproject.springboot_project;

import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import com.consultaddtraining.javaproject.springboot_project.dto.UserDTO;
import com.consultaddtraining.javaproject.springboot_project.exceptions.ResourceNotFoundException;
import com.consultaddtraining.javaproject.springboot_project.repositories.UserRepository;
import com.consultaddtraining.javaproject.springboot_project.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootProjectApplicationTests {

	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;

//	@Test
//	void contextLoads() {
//	}


	@Test
	public void testGetAllUsers(){
		// mock repo data
		when(userRepository.findAll())
				.thenReturn(Stream.of(
						UserEntity.builder().id(1L).name("abc").email("abc@gmail.com").password("ujfijork^23*#)n").build(),
						UserEntity.builder().id(2L).name("sample").email("sample@gmail.com").password("3jdfi9#yw^23*#)n").build(),
						UserEntity.builder().id(3L).name("demo").email("demo@gmail.com").password("ujfi000@$6*#)n").build()
				).collect(Collectors.toList()));
		assertEquals(3, userService.getAllUsers().size());
	}

	@Test
	public void testGetUserById(){
		Long id = 346L;
		// mock repo data
		when(userRepository.findById(id))
				.thenReturn(
						Optional.of(UserEntity.builder().id(id).name("abc").email("abc@gmail.com").password("ujfijork^23*#)n").build()));
		assertEquals(id, userService.getUserById(id).getId());

		// when user not found
		Long id2 = 7L;
		when(userRepository.findById(id2))
				.thenThrow(ResourceNotFoundException.class);

		assertEquals(id, userService.getUserById(id).getId());

//		assertEquals("User not found", 	// check exception msg
				assertThrows( ResourceNotFoundException.class, () -> userService.getUserById(id2));	// check if exception thrown
//				.getMessage());
	}

	@Test
	public void testDeleteUser(){
		Long id = 15L;
		userService.deleteUser(id);

		//check if method was called correctly
		verify(userRepository).deleteById(id);	// mockito method

	}

	@Test
	public void testUpdateUser(){
		Long id = 15L;

		UserEntity olduser = UserEntity.builder().id(id).name("abc").email("abc@gmail.com").password("ujfijork^23*#)n").build();
		when(userRepository.findById(id)).thenReturn(Optional.of(olduser));

		UserDTO newuser = UserDTO.builder().id(id).name("abc").email("abc1234@gmail.com").build();
		UserEntity newuserentity = UserEntity.builder().id(id).name("abc").email("abc1234@gmail.com").build();
		when(userRepository.save(newuserentity)).thenReturn(newuserentity);

		assertEquals(id, userService.updateUser(id, newuser).getId());
	}

}