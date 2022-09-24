package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class MyRestController {
    @PostMapping("/createStudent")
    public RedirectView CreateStudent(String firstName, String lastName){
        Application.studentService.CreateStudent(firstName, lastName);
        return new RedirectView("/");
    }

    @PostMapping("/createOnlineCourse")
    public RedirectView CreateOnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info){
        Application.studentService.CreateOnlineCourse(id, name, teacher, startDate, endDate, credit, remoteLink, info);
        return new RedirectView("/");
    }

    @PostMapping("/createClassRoomCourse")
    public RedirectView CreateClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info){
        Application.studentService.CreateClassRoomCourse(id, name, teacher, startDate, endDate, credit, classRoom, info);
        return new RedirectView("/");
    }

    @PostMapping("/addStudentToCourse")
    public RedirectView AddStudentToCourse(String studentID, String courseID){
        System.out.println(studentID + " " + courseID);
        Application.studentService.AddStudentToCourse(studentID, courseID);
        
        return new RedirectView("/students");
    }

    

}

