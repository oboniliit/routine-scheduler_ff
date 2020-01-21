/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gal.cs;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author ddxofy
 */
public class Population {
    
    private ArrayList<Schedule> schedules;

    public Population(int size, Data data) {
        schedules = new ArrayList<Schedule>(size);
        IntStream.range(0,size).forEach(x -> schedules.add(new Schedule(data).initialize()));
    }
    
    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
    
    public Population sortByFitness(){
        schedules.sort((Schedule schedule1, Schedule schedule2) -> {
            int returnValue = 0;
            if(schedule1.getFitness()>schedule2.getFitness()) returnValue = -1;
            if(schedule1.getFitness()<schedule2.getFitness()) returnValue = 1;
            return returnValue;
        });
        return this;
    }
    
}
