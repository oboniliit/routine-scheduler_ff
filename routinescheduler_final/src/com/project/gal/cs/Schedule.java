/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gal.cs;

import com.project.gal.cs.models.Course;
import com.project.gal.cs.models.Department;
import com.project.gal.cs.models.MyClass;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ddxofy
 */
public class Schedule {

    private ArrayList<MyClass> classes;
    private int classNumb = 0;
    private int numberOfConflicts = 0;
    private double fitness = 0;
    private boolean isFitnessChanged = true;
    private Data data;

    public Schedule(Data data) {
        this.data = data;
        classes = new ArrayList<MyClass>(data.getNumberOfClasses());
    }

    public Schedule initialize() {
        Map<String, Double> tempcoursecreditmap = data.getCoursecreditsmap();
        new ArrayList<Department>(data.getDepts()).forEach((dept) -> {
            ArrayList<Course> courses = dept.getCourses();
            courses.forEach(course -> {
                MyClass newClass = new MyClass(classNumb++, dept, course);
                if (!isLabCourse(course)) {
                    newClass.setMeetingtime(data.getNonlabmeetingtimes().get((int) (data.getNonlabmeetingtimes().size() * Math.random())));
                } else {
                    newClass.setMeetingtime(data.getLabmeetingtimes().get((int) (data.getLabmeetingtimes().size() * Math.random())));
                }
//                newClass.setInstructor(data.getInstructors().get((int) (data.getInstructors().size() * Math.random())));
                data.getInstructors().forEach(ins -> {
                    if (ins.getId().compareTo(course.getInstructor().get(0).getId()) == 0) {
                        newClass.setInstructor(ins);
                    }
                });
                newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
                classes.add(newClass);
                if (tempcoursecreditmap.containsKey(course.getNumber()) && tempcoursecreditmap.get(course.getNumber()) == 3.0) {

                    MyClass newClass2 = new MyClass(classNumb++, dept, course);
                    if (!isLabCourse(course)) {
                        newClass2.setMeetingtime(data.getNonlabmeetingtimes().get((int) (data.getNonlabmeetingtimes().size() * Math.random())));
                    } else {
                        newClass2.setMeetingtime(data.getLabmeetingtimes().get((int) (data.getLabmeetingtimes().size() * Math.random())));
                    }
//                newClass.setInstructor(data.getInstructors().get((int) (data.getInstructors().size() * Math.random())));
                    data.getInstructors().forEach(ins -> {
                        if (ins.getId().compareTo(course.getInstructor().get(0).getId()) == 0) {
                            newClass2.setInstructor(ins);
                        }
                    });
                    newClass2.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
                    classes.add(newClass2);
                }
                //System.out.println(course.getInstructor().get(0));
            });
        });
        return this;
    }

    private double calculateFitness() {
        numberOfConflicts = 0;
        classes.forEach(x -> {
            if (x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumberOfStudents()) {
                numberOfConflicts++;
            }

            classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
                if (x.getMeetingtime() == y.getMeetingtime() && x.getId() != y.getId()) {
           
                    if (x.getRoom() == y.getRoom()) {
                        numberOfConflicts++;
                    }
                    if (x.getInstructor() == y.getInstructor()) {
                        numberOfConflicts++;
                    }
                    if (semesterConflict(x, y)) {
                        numberOfConflicts++;
                    }
                };
                    
                if (x.getCourse().getNumber().compareTo(y.getCourse().getNumber()) == 0 && x.getMeetingtime() != y.getMeetingtime()){
                    String a = x.getMeetingtime().getTime();
                    String b = y.getMeetingtime().getTime();
                    if (a.substring(0,2).compareTo(b.substring(0,2))==0){
                        numberOfConflicts++;
                    }
                }
            });
        });
        return 1 / (double) (numberOfConflicts + 1);
    }

    public Data getData() {
        return data;
    }

    boolean isLabCourse(Course a) {
        String courseIdA = a.getNumber();
        int isLab = (courseIdA.charAt(courseIdA.length()-1) - '0');
        isLab = isLab % 2;
        if (isLab == 0) {
            return true;
        }
        return false;
    }

    boolean semesterConflict(MyClass a, MyClass b) {
        String courseIdA = a.getCourse().getNumber();
        String courseIdB = b.getCourse().getNumber();
        int semesterA = 10 * (courseIdA.charAt(3) - '0') + (courseIdA.charAt(4) - '0');
        int semesterB = 10 * (courseIdB.charAt(3) - '0') + (courseIdB.charAt(4) - '0');
        if (semesterA == semesterB) {
            return true;
        }
        return false;
    }

    public int getNumberOfConflicts() {
        return numberOfConflicts;
    }

    public ArrayList<MyClass> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness() {
        if (isFitnessChanged == true) {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    @Override
    public String toString() {
        String returnValue = new String();
        for (int i = 0; i < classes.size() - 1; i++) {
            returnValue += classes.get(i) + ", ";
        }
        returnValue += classes.get(classes.size() - 1);
        return returnValue;
    }

}
