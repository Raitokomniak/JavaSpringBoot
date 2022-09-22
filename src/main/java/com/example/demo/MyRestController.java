package com.example.demo;


import java.util.ArrayList;
import java.util.List;

/*Pieni Spring Boot -sovellus, joka hyödyntää edellä toteutettua rakennetta. Tähän osioon kuuluu lähinnä RESTrajapinta, joka tarjoaa käyttäjälle HTTP:n kautta käyttöliittymätoiminnot, kuten kurssien hakemisen
selaimeen ja uuden tiedon lisääminen. Tiedon lisäämisen voi toteuttaa toimimaan esim. Postmanilla JSONkutsuina tai tehdä jopa html-lomakkeilla kevyen käyttöliittymän (staattiset sivut Spring Bootissa). Ohjelmaa
voi laajentaa halutessaan tukemaan myös monipuolisempia toimintoja kuten opiskelijan lisääminen tietylle
kurssille jne.
Jos REST ei ole hallussa, voi pienen käyttöliittymän tehdä myös konsolisovelluksena. REST-rajapinnan arvo on
kuitenkin suurempi arvioinnissa. */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyRestController {

    @GetMapping("/courses")
    public String Courses(){
        return "Hello courses";
    }

    @GetMapping("/students")
    public List<String> Students(){
        List<String> nameList = new ArrayList<String>();
        for(Student s : Application.studentService.GetAllStudents()){
            nameList.add(s.firstName + " " + s.lastName);
        };
        return nameList;
    }

    @PostMapping("/createStudent")
    public String CreateStudent(String firstName, String lastName){
        Application.studentService.CreateStudent(firstName, lastName);
        return "Created student " + firstName + " " + lastName;
    }

    /*
    @PostMapping("/createOnlineCourse")
    public void CreateOnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info){
        Application.studentService.CreateOnlineCourse(id, name, teacher, startDate, endDate, credit, remoteLink, info);
    }

    @PostMapping("/createClassRoomCourse")
    public void CreateClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info){
        Application.studentService.CreateClassRoomCourse(id, name, teacher, startDate, endDate, credit, classRoom, info);
    }
     */

}

