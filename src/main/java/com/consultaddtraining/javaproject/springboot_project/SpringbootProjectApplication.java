package com.consultaddtraining.javaproject.springboot_project;

import com.consultaddtraining.javaproject.springboot_project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootProjectApplication implements CommandLineRunner {

	@Autowired
	static  PostService postService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProjectApplication.class, args);
//		postService
	}

	@Override
	public void run(String... args) throws Exception {
		postService.getPostById(1L);
	}
}