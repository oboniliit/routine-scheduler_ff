/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Farhin
 */
public class RoutineClass {

    private final StringProperty courseid;
    private final StringProperty teacherid;
    private final StringProperty room;
    private final StringProperty time;
    private final IntegerProperty order;
    private final StringProperty id;

    public RoutineClass(String courseid, String teacherid, String room, String time, Integer order, String id) {
        this.courseid = new SimpleStringProperty(courseid);
        this.teacherid = new SimpleStringProperty(teacherid);
        this.room = new SimpleStringProperty(room);
        this.time = new SimpleStringProperty(time);
        this.order = new SimpleIntegerProperty(order);
        this.id = new SimpleStringProperty(id);
    }

    public String getCourseid() {
        return courseid.get();
    }

    public String getTeacherid() {
        return teacherid.get();
    }

    public String getRoom() {
        return room.get();
    }

    public String getTime() {
        return time.get();
    }
    
    public Integer getOrder(){
        return order.get();
    }
    
    public String getId(){
        return id.get();
    }
    
    public void setCourseid(String value) {
        courseid.set(value);
    }

    public void setTeacherid(String value) {
        teacherid.set(value);
    }

    public void setRoom(String value) {
        room.set(value);
    }

    public void setTime(String value) {
        time.set(value);
    }
    
    public void setOrder(Integer value){
        order.set(value);
    }
    
    public void setId(String value){
        id.set(value);
    }
    
    public StringProperty courseidProperty() {
        return courseid;
    }

    public StringProperty teacheridProperty() {
        return teacherid;
    }

    public StringProperty roomProperty() {
        return room;
    }

    public StringProperty timeProperty() {
        return time;
    }
    
    public IntegerProperty orderProperty(){
        return order;
    }
    
    public StringProperty idProperty(){
        return id;
    }
}
