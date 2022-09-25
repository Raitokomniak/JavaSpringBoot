package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Course implements Serializable so that it can be saved as an object
public abstract class Course implements Serializable{
    String id;
    String name;
    String teacher;
    String startDate;
    String endDate;
    int credit;
    String info;
    List<Student> students;

    public Course(){}

    public Course(String id, String name, String teacher, String startDate, String endDate, int credit, String info){
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credit = credit;
        this.info = info;
        students = new ArrayList<Student>();
    }

    public void AddStudentToCourse(Student student){ students.add(student); }

    public void RemoveStudentFromCourse(Student student){
        for(Student s : students){
            if(s.equals(student)){
                students.remove(s);
                return;
            }
        }
    }

    public String GetID(){return id;}
    public String GetName(){return name;}
    public String GetTeacher(){return teacher;}
    public String GetStartDate(){return startDate;}
    public String GetEndDate(){return endDate;}
    public int GetCredit(){return credit;}
    public String GetInfo(){return info;}
    public List<Student> GetStudents(){return students;}
}