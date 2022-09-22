package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String Index(Model model){
        model.addAttribute("students", Application.studentService.GetAllStudents());
        model.addAttribute("courses", Application.studentService.GetAllCourses());
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

    @RequestMapping("/students")
    public String ListStudents(){
        return "students";
    }

    @RequestMapping("/courses")
    public String ListCourses(){
        return "courses";
    }


}
