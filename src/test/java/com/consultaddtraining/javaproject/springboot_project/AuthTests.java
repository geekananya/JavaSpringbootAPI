package com.consultaddtraining.javaproject.springboot_project;

import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import com.consultaddtraining.javaproject.springboot_project.dto.UserLoginDTO;
import com.consultaddtraining.javaproject.springboot_project.dto.UserRegisterDTO;
import com.consultaddtraining.javaproject.springboot_project.repositories.UserRepository;
import com.consultaddtraining.javaproject.springboot_project.services.AuthService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTests {

    @Autowired
    AuthService authService;

    @MockBean
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        try (var mocks = MockitoAnnotations.openMocks(this)) {
            System.out.println(mocks);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize mocks", e);
        }
    }

    @AfterEach
    public void tearDown() {
        reset();
    }


    @Test
    public void testSignup(){
        UserRegisterDTO dto = new UserRegisterDTO("sample user","sample@user.com","secret");
        UserEntity entity = UserEntity.builder().name("sample user").email("sample@user.com").password("secret").build();
        when(modelMapper.map(dto, UserEntity.class)).thenReturn(entity);

        when(passwordEncoder.encode("secret")).thenReturn("i&93vs2f#9*@3");

        UserEntity entitytosave = UserEntity.builder().name("sample user").email("sample@user.com").password("i&93vs2f#9*@3").build();
        UserEntity entitysaved = UserEntity.builder().
                id(3L).
                name("sample user").
                email("sample@user.com").
                password("i&93vs2f#9*@3").
                isActive(true).
                createdAt(LocalTime.now()).
                build();

        when(userRepository.save(entitytosave)).thenReturn(entitysaved);

        assertEquals(Optional.of(3L), authService.signup(dto).getId());
    }

    @Test
    public void testAuthenticate(){
        UserLoginDTO dto = new UserLoginDTO("sample@user.com","secret");
        UserEntity entity = UserEntity.builder().name("sample user").email("sample@user.com").password("i&93vs2f#9*@3").build();

        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword()
        ));

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.ofNullable(entity));

        assertEquals(entity, authService.authenticate(dto));
//        assertThrows()
    }
}
