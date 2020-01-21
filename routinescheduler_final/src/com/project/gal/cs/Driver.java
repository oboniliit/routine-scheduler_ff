/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gal.cs;

import DAO.AppClass;
import com.project.gal.cs.models.MyClass;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author ddxofy
 */
public class Driver {

    public static final int POPULATION_SIZE = 9;
    public static final double MUTATION_RATE = 0.1;
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMBER_OF_ELITE_SCHEDULES = 1;
    private int scheduleNumber = 0;
    private int classNumb = 1;
    private Data data;
    private ArrayList<AppClass> userinputclass;

    public Driver(ArrayList<AppClass> userInputClass) {
        userinputclass = userInputClass;
    }

    public Schedule runAI() {
        this.data = new Data(userinputclass);
        int generationNumber = 0;
        this.PrintAvailableData();
        System.out.println("> Generation #: " + generationNumber++);
        System.out.print(" Schedule# |                                       ");
        System.out.print("Calsses [dept,calss,room,instructor,meeting-time]   ");
        System.out.println("                                              | Fitness | Conflicts");
        System.out.print("-----------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(this.data);
        Population population = new Population(Driver.POPULATION_SIZE, this.data).sortByFitness();
        population.getSchedules().forEach(x -> System.out.println("           " + this.scheduleNumber++
                + " | " + x + "  |  "
                + String.format("%.5f", x.getFitness())
                + "  |  " + x.getNumberOfConflicts()));
        this.PrintScheduleAsTable(population.getSchedules().get(0), generationNumber);
        this.classNumb = 1;
        while (population.getSchedules().get(0).getFitness() != 1.0) {
            System.out.println("> Generation #: " + generationNumber++);
            System.out.print(" Schedule# |                                       ");
            System.out.print("Calsses [dept,calss,room,instructor,meeting-time]   ");
            System.out.println("                                              | Fitness | Conflicts");
            System.out.print("-----------------------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------");
            population = geneticAlgorithm.evolve(population).sortByFitness();
            population.getSchedules().forEach(x -> System.out.println("           " + this.scheduleNumber++
                    + " | " + x + "  |  "
                    + String.format("%.5f", x.getFitness())
                    + "  |  " + x.getNumberOfConflicts()));
            this.PrintScheduleAsTable(population.getSchedules().get(0), generationNumber);
            this.classNumb = 1;
        }
        return population.getSchedules().get(0);

    }

    private void PrintScheduleAsTable(Schedule schedule, int generationnumber) {
        ArrayList<MyClass> classes = schedule.getClasses();
        System.out.print("\n                                 ");
        System.out.println("Calss # | Dept | Course (number, max # of Students)                     | Room(Capacity) |  Instructor(id)  | Meeting-Time(Id, Time)  ");
        System.out.print("                                   ");
        System.out.print("-----------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------");
        classes.forEach(x -> {
            int majorsIndex = data.getDepts().indexOf(x.getDept());
            int coursesIndex = data.getCourses().indexOf(x.getCourse());
            int roomsIndex = data.getRooms().indexOf(x.getRoom());
            int instructorsIndex = data.getInstructors().indexOf(x.getInstructor());
            int meetingTimesIndex = data.getMeetingtimes().indexOf(x.getMeetingtime());
            System.out.print("                                 ");
            System.out.print(String.format(" %1$02d ", classNumb) + " | ");
            System.out.print(String.format("%1$4s ", data.getDepts().get(majorsIndex).getName()) + " | ");
            System.out.print(String.format("%1$41s ", data.getCourses().get(coursesIndex).getName()
                    + "( " + data.getCourses().get(coursesIndex).getNumber() + ", "
                    + x.getCourse().getMaxNumberOfStudents()) + ")             | ");
            System.out.print(String.format("%1$10s ", data.getRooms().get(roomsIndex).getNumber()
                    + " (" + data.getRooms().get(roomsIndex).getSeatingCapacity()) + ")    | ");
            System.out.print(String.format("%1$15s ", data.getInstructors().get(instructorsIndex).getName()
                    + " (" + data.getInstructors().get(instructorsIndex).getId() + ") ") + "   | ");
            System.out.println(data.getMeetingtimes().get(meetingTimesIndex).getTime()
                    + "(" + data.getMeetingtimes().get(meetingTimesIndex).getId() + ")");
            classNumb++;
        });
        if (schedule.getFitness() == 1) {
            System.out.println("> Solution found in " + (generationnumber + 1) + " generations.");
        }
        System.out.print("-----------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------");
    }

    private void PrintAvailableData() {
        System.out.println("Available Departmens ==>");
        data.getDepts().forEach(x -> System.out.println("name: " + x.getName() + ", courses:" + x.getCourses()));
        System.out.println("\nAvailable Courses ==>");
        data.getCourses().forEach(x -> System.out.println("Course #: " + x.getNumber() + ", name: " + x.getName()
                + ", max # of students: " + x.getMaxNumberOfStudents() + ", instructors: " + x.getInstructor()));
        System.out.println("\nAvailable Rooms ==>");
        data.getRooms().forEach(x -> System.out.println("room #: " + x.getNumber() + ", maximumSeatingCapacity: " + x.getSeatingCapacity()));
        System.out.println("\nAvailable Instructors ==>");
        data.getInstructors().forEach(x -> System.out.println("id: " + x.getId() + ", name: " + x.getName()));
        System.out.println("\nAvailable Meeting Times ==>");
        data.getMeetingtimes().forEach(x -> System.out.println("id: " + x.getId() + ", time: " + x.getTime()));
        System.out.print("-----------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------");

    }
}
