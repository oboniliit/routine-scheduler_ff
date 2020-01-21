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
public class Course {

    private String number = null;
    private String name = null;
    private int maxNumberOfStudents;
    private ArrayList<Instructor> instructor;

    public Course(String number, String name, ArrayList<Instructor> instructor, int maxNumberOfStudents) {
        this.number = number;
        this.name = name;
        this.instructor = instructor;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public ArrayList<Instructor> getInstructor() {
        return instructor;
    }
}
