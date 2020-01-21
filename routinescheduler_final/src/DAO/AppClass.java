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
 * @author ddxofy
 */
public class AppClass {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty teacher_name;
    private final StringProperty teacher_id;

    public AppClass(String id, String title, String teacher_name, String teacher_id) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.teacher_name = new SimpleStringProperty(teacher_name);
        this.teacher_id = new SimpleStringProperty(teacher_id);
    }

    public String getId() {
        return id.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getTeacher_name() {
        return teacher_name.get();
    }

    public String getTeacher_id() {
        return teacher_id.get();
    }
    
    public void setId(String value) {
        id.set(value);
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public void setTeacher_name(String value) {
        teacher_name.set(value);
    }

    public void setTeacher_id(String value) {
        teacher_id.set(value);
    }
    public StringProperty idProperty(){return id;}
    public StringProperty titleProperty(){return title;}
    public StringProperty teacher_nameProperty(){return teacher_name;}
    public StringProperty teacher_idProperty(){return teacher_id;}
}
