/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Farhin
 */
public class Course {

    private final StringProperty id;
    private final StringProperty title;
    private final DoubleProperty credit;

    public Course(String id, String title, Double credit) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.credit = new SimpleDoubleProperty(credit);
    }

    //Getters
    public String getId() {
        return id.get();
    }

    public String getTitle() {
        return title.get();
    }

    public Double getCredit() {
        return credit.get();
    }

    //Setters
    public void setId(String value) {
        id.set(value);
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public void setCredit(Double value) {
        credit.set(value);
    }
    
    public StringProperty idProperty(){return id;}
    public StringProperty titleProperty(){return title;}
    public DoubleProperty creditProperty(){return credit;}

    @Override
    public String toString() {
        return getId();
    }
    
    

}
