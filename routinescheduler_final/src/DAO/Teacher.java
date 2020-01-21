/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Farhin
 */
public class Teacher {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty designation;
    private final StringProperty department;
    private final StringProperty email;
    
    public Teacher(String id, String name, String designation, String depatment, String email) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.designation = new SimpleStringProperty(designation);
        this.department = new SimpleStringProperty(depatment);
        this.email = new SimpleStringProperty(email);

    }
    //getters

    public String getName() {
        return name.get();
    }

    public String getDesignation() {
        return designation.get();
    }

    public String getDepartment() {
        return department.get();
    }

    public String getId() {
        return id.get();
    }
    
    public String getEmail() {
        return email.get();
    }
    
    //setters

    public void setId(String value) {
        id.set(value);
    }

    public void setName(String value) {
        name.set(value);
    }

    public void setDesignation(String value) {
        designation.set(value);
    }

    public void setDepartment(String value) {
        department.set(value);
    }
    
    public void setEmail(String value) {
        email.set(value);
    }

    //property
    
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty designationProperty() {
        return designation;
    }

    public StringProperty departmentProperty() {
        return department;
    }
    
    public StringProperty emailProperty() {
        return email;
    }

    @Override
    public String toString() {
        return getName();
    }

   

}
