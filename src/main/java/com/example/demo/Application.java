package com.example.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static StudentService studentService;
    public static FileService fileService;

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
        System.setProperty("file.encoding","UTF-8");
        Properties prop = new Properties();
        String fileName = "app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        studentService = new ServiceInstance();
        fileService = new FileService();
	}
}