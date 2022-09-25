package com.example.demo;
import java.io.Serializable;

//Student implements Serializable so that it can be saved as an object
class Student implements Serializable {
    String firstName;
    String lastName;
    String id;
    //List<Course> courses; // students could theoratically track courses but for this exercise unnecessary

    public Student(){}
    
    public Student(String firstName, String lastName, String id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    //Getters
    public String GetFirstName(){ return firstName; }
    public String GetLastName(){ return lastName; }
    public String GetID(){   return id;}
}
