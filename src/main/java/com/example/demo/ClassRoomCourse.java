package com.example.demo;

public class ClassRoomCourse extends Course {
    String classRoom;

    public ClassRoomCourse(){}

    public ClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info){
        super(id, name, teacher, startDate, endDate, credit, info);
        this.classRoom = classRoom;
        courseType = "classroom";
    }

    public void EditCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info){
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credit = credit;
        this.classRoom = classRoom;
        this.info = info;
    }

    public String GetClassRoom(){return classRoom;}
}
