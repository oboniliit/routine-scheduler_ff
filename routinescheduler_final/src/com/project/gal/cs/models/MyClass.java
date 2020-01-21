/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gal.cs.models;

import java.util.ArrayList;

/**
 *
 * @author ddxofy
 */
public class MyClass {
    private int id;
    private Department dept;
    private Course course;
    private Instructor instructor;
    private MeetingTime meetingtime;
    private Room room;

    public MyClass(int id, Department dept, Course course) {
        this.id = id;
        this.dept = dept;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public Department getDept() {
        return dept;
    }

    public Course getCourse() {
        return course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public MeetingTime getMeetingtime() {
        return meetingtime;
    }

    public Room getRoom() {
        return room;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setMeetingtime(MeetingTime meetingtime) {
        this.meetingtime = meetingtime;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "[ " + dept.getName() + "," + course.getNumber() + "," + instructor.getId() + "," + meetingtime.getId() + "," + room.getNumber() + ']';
    }
    
    
    
}
