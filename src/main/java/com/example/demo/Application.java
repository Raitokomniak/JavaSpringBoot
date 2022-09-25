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

    public static void main(String[] args) throws IOException {
        //Run spring app
		SpringApplication.run(Application.class, args);

        //Set UTF-8 encoding
        System.setProperty("file.encoding","UTF-8");

        //Set properties for Thymeleaf
        Properties prop = new Properties();
        String fileName = "app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        //Create new service instances
        studentService = new ServiceInstance();
        fileService = new FileService();

        //Auto-load StudentInfo.dat and CourseInfo.dat
        studentService.SetLoadedCourses(fileService.LoadCourseInfo());
        studentService.SetLoadedStudents(fileService.LoadStudentInfo());
	}
}