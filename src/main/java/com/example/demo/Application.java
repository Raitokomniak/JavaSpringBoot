package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static StudentService studentService;
    public static FileService fileService;
    
    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

        studentService = new ServiceInstance();
        fileService = new FileService();
	}
}