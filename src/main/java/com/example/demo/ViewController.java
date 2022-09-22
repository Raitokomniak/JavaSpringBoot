package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/students")
    public void welcome(Model model) {
        List<Student> students = Application.studentService.GetAllStudents();
        model.addAttribute("students", students);
    } 
}
