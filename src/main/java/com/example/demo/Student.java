package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Student implements Serializable {
    String firstName;
    String lastName;
    String id;
    List<Course> courses;

    public Student(){}
    
    public Student(String firstName, String lastName, String id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        courses = new ArrayList<Course>();
    }

    public void AddToCourse(Course course){
        courses.add(course);
    }

    public void RemoveFromCourse(Course course){
        courses.remove(course);
    }

    //Getters
    public String GetFirstName(){ return firstName; }
    public String GetLastName(){ return lastName; }
    public String GetID(){   return id;}
    public List<Course> GetCourses(){ return courses; }
}
