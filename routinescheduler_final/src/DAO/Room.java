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
public class Room {
    
    private final StringProperty no;
    private final IntegerProperty floor;
    private final StringProperty type;
    private final IntegerProperty capacity;
    
    public Room(String no, int floor, String type, int capacity) {
        this.no = new SimpleStringProperty(no);
        this.floor = new SimpleIntegerProperty(floor);
        this.type = new SimpleStringProperty(type);
        this.capacity = new SimpleIntegerProperty(capacity);

    }

    public String getNo() {
        return no.get();
    }

    public int getFloor() {
        return floor.get();
    }

    public String getType() {
        return type.get();
    }

    public int getCapacity() {
        return capacity.get();
    }

    public void setNo(String value) {
        no.set(value);
    }

    public void setFloor(int value) {
        floor.set(value);
    }

    public void setType(String value) {
        type.set(value);
    }

    public void setCapacity(int value) {
        capacity.set(value);
    }

    public StringProperty noProperty() {
        return no;
    }

    public IntegerProperty floorProperty() {
        return floor;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    
}
