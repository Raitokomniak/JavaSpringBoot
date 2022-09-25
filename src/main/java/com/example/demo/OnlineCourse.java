package com.example.demo;

public class OnlineCourse extends Course{
    String remoteLink; //Link to a zoom meeting, live stream etc.
    
    public OnlineCourse(){}

    public OnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info){
        super(id, name, teacher, startDate, endDate, credit, info);
        this.remoteLink = remoteLink;
    }

    public void EditCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info){
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credit = credit;
        this.remoteLink = remoteLink;
        this.info = info;
    }

    public String GetLink(){ return remoteLink; }
}