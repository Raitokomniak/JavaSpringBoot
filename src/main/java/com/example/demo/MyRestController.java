package com.example.demo;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class MyRestController {
    @PostMapping("/createStudent")
    public RedirectView CreateStudent(String firstName, String lastName) throws IOException{
        Application.studentService.CreateStudent(firstName, lastName);
        return new RedirectView("/students");
    }

    @PostMapping("/createOnlineCourse")
    public RedirectView CreateOnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info) throws IOException{
        Application.studentService.CreateOnlineCourse(id, name, teacher, startDate, endDate, credit, remoteLink, info);
        return new RedirectView("/courses");
    }

    @PostMapping("/createClassRoomCourse")
    public RedirectView CreateClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info) throws IOException{
        Application.studentService.CreateClassRoomCourse(id, name, teacher, startDate, endDate, credit, classRoom, info);
        return new RedirectView("/courses");
    }

    @PostMapping("/addStudentToCourse")
    public RedirectView AddStudentToCourse(@RequestParam String studentID, @RequestParam String courseID) throws IOException{
        System.out.println("student " + studentID);
        
        if(studentID != null && courseID != null)
            Application.studentService.AddStudentToCourse(studentID, courseID);
        else {
            System.out.println(studentID + " " + courseID);
            System.out.println("one is null");
        }
        
        return new RedirectView("/students");
    }

    @PostMapping("/removeStudentFromCourse")
    public RedirectView RemoveStudentToCourse(String studentID, String courseID) throws IOException{
        if(studentID != null && courseID != null)
            Application.studentService.RemoveStudentFromCourse(studentID, courseID);
        else {
            System.out.println(studentID + " " + courseID);
            System.out.println("one is null");
        }
        return new RedirectView("/course?courseID=" + courseID);
    }

    @PostMapping("/deleteStudent")
    public RedirectView DeleteStudent(String studentID) throws IOException{
        Application.studentService.DeleteStudent(studentID);
        return new RedirectView("/students");
    }

    @PostMapping("/deleteCourse")
    public RedirectView DeleteCourse(String courseID) throws IOException{
        Application.studentService.DeleteCourse(courseID);
        return new RedirectView("/courses");
    }

    @PostMapping("/saveEditedCourse")
    public RedirectView EditCourse(String courseID, String id, String name, String teacher, String startDate, String endDate, int credit, String location, String info) throws IOException{
        Course c = Application.studentService.FindCourseByID(courseID);

        if(c instanceof OnlineCourse){
            OnlineCourse oc = (OnlineCourse) c;
            oc.EditCourse(id, name, teacher, startDate, endDate, credit, location, info);
        }
        else if(c instanceof ClassRoomCourse) {
            ClassRoomCourse cc = (ClassRoomCourse) c;
            cc.EditCourse(id, name, teacher, startDate, endDate, credit, location, info);
        }
       
        return new RedirectView("/course?courseID=" + courseID);
    }

    @PostMapping("/editCourse")
    public RedirectView EditCourse(@RequestParam String courseID) throws IOException{
        return new RedirectView("/course?courseID=" + courseID);
    }

    @GetMapping("/loadPremadeContent")
    public RedirectView LoadPremadeContent() throws IOException{
        Application.fileService.LoadPremadeContent();
        return new RedirectView("/");
    }

    @GetMapping("/flushAllContent")
    public RedirectView FlushAllContent(){
        Application.fileService.FlushAllContent();
        return new RedirectView("/");
    }

    @GetMapping("/getCourse")
    public RedirectView GetCourse(@RequestParam String courseID){
        return new RedirectView("/course?courseID=" + courseID);
    }
}

