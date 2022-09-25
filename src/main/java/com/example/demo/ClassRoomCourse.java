package com.example.demo;

public class ClassRoomCourse extends Course {
    String classRoom;

    public ClassRoomCourse(){}

    public ClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info){
        super(id, name, teacher, startDate, endDate, credit, info);
        this.classRoom = classRoom;
        courseType = "classroom";
    }

    public String GetClassRoom(){return classRoom;}
}
