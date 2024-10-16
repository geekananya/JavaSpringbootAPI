package com.consultaddtraining.javaproject.springboot_project.configuration;

import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import com.consultaddtraining.javaproject.springboot_project.dto.UserLoginDTO;
import com.consultaddtraining.javaproject.springboot_project.dto.UserRegisterDTO;
import com.consultaddtraining.javaproject.springboot_project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class appConfig {
    private final UserRepository userRepository;

    public appConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public ModelMapper modelMapper(PasswordEncoder passwordEncoder) {
        ModelMapper modelMapper = new ModelMapper();

//        modelMapper.addMappings(new PropertyMap<UserRegisterDTO, UserEntity>() {
//            @Override
//            protected void configure() {
//                map().setEmail(source.getEmail());
//                map().setName(source.getName());
//                map().setPassword(passwordEncoder.encode(source.getPassword()));
//            }
//        });

        return modelMapper;
    }


    // config auth mgr(in login) to authenticate using email and password like this.
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
