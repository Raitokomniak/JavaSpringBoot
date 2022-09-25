package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String Index(){
        return "index";
    }

    @RequestMapping("/index.html")
    public String StaticIndex(){
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
        return "students";
    }

    @RequestMapping("/courses")
    public String ListCourses(Model model){
        model.addAttribute("courses", Application.studentService.GetAllCourses());
        model.addAttribute("students", Application.studentService.GetAllStudents());
        return "courses";
    }

    @RequestMapping("/course")
    public String CourseInfo(@RequestParam String courseID, Model model){
        model = Application.studentService.GetCourseInfo(courseID, model);
        
        return "course";
    }

    @RequestMapping("/editcourse")
    public String EditCourseInfo(@RequestParam String courseID, Model model){
        Course course = Application.studentService.FindCourseByID(courseID);
        model.addAttribute("course", course);

        if(course instanceof OnlineCourse){
            OnlineCourse oc = (OnlineCourse) course;
            model.addAttribute("location", oc.GetLink());
        }
        if(course instanceof ClassRoomCourse){
            ClassRoomCourse cc = (ClassRoomCourse) course;
            model.addAttribute("location", cc.GetClassRoom());
        }
        
        return "editcourse";
    }


}
