package com.example.demo;

public class OnlineCourse extends Course{
    String remoteLink; //Link to a zoom-meeting, live stream etc.
    
    public OnlineCourse(){}

    public OnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info){
        super(id, name, teacher, startDate, endDate, credit, info);
        this.remoteLink = remoteLink;
        courseType = "online";
    }

    public String GetLink(){ return remoteLink; }
}