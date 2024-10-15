package com.consultaddtraining.javaproject.springboot_project.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
