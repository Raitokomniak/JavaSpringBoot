package com.example.demo;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String Index(Model model){
        return "index";
    }

    @RequestMapping("/courseRedirect")
    public String CourseRedirect(String courseType){
        return "create" + courseType + "form";
    }

    @RequestMapping("/createonlinecourseform")
    public String CreateOnlineCourseForm(){
        return "createonlinecourseform";
    }

    @RequestMapping("/createclassroomcourseform")
    public String CreateClassRoomCourseForm(){
        return "createonlinecourseform";
    }

    @RequestMapping("/createStudent")
    public String CreateStudentRedirect(){
        return "redirect:" + "/index.html";
    }

    @RequestMapping("/createStudentForm")
    public String CreateStudentForm(){
        return "createstudentform";
    }

    @RequestMapping("/students")
    public String ListStudents(Model model){
        model.addAttribute("students", Application.studentService.GetAllStudents());
        model.addAttribute("courses", Application.studentService.GetAllCourses());
        int index = 0;
        for(Course c : Application.studentService.GetAllCourses()){
            model.addAttribute(String.valueOf(index++), c.GetID());
        }
        return "students";
    }

    @RequestMapping("/courses")
    public String ListCourses(Model model){
        model.addAttribute("courses", Application.studentService.GetAllCourses());
        model.addAttribute("students", Application.studentService.GetAllStudents());
        return "courses";
    }


}
