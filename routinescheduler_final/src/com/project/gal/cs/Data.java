/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gal.cs;

import DAO.AppClass;
import com.project.gal.cs.models.Instructor;
import com.project.gal.cs.models.MeetingTime;
import com.project.gal.cs.models.Department;
import com.project.gal.cs.models.Room;
import com.project.gal.cs.models.Course;
import database.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author ddxofy
 */
public class Data {

    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Department> depts;
    private ArrayList<MeetingTime> meetingtimes;
    private ArrayList<MeetingTime> labmeetingtimes;
    private ArrayList<MeetingTime> nonlabmeetingtimes;
    private Map<String, Double> coursecreditsmap;

    private int numberOfClasses = 0;

    DBConnector handler = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    String selectRoomSQL = "SELECT * FROM room";

    public Data(ArrayList<AppClass> data) {
        handler = DBConnector.getInstance();
        initialize(data);
    }

    private Data initialize(ArrayList<AppClass> data) {
        rooms = new ArrayList<Room>();
        coursecreditsmap = new HashMap<>();
        try {
            pre = handler.conn.prepareStatement(selectRoomSQL);
            rs = pre.executeQuery();
            while (rs.next()) {
                rooms.add(new Room(rs.getString(1), new Integer(rs.getString(4))));
            }
            pre.close();
            rs.close();
        } catch (SQLException e) {
        }
        try {
            pre = handler.conn.prepareStatement("SELECT * FROM course");
            rs = pre.executeQuery();
            while (rs.next()) {
                coursecreditsmap.put(rs.getString(1),new Double(rs.getString(3)));
            }
            pre.close();
            rs.close();
        } catch (SQLException e) {
        }

        MeetingTime mt1 = new MeetingTime("MT1", "SUN 08:30 - 09:50");
        MeetingTime mt2 = new MeetingTime("MT2", "SUN 10:00 - 11:20");
        MeetingTime mt3 = new MeetingTime("MT3", "SUN 11:30 - 12:50");
        MeetingTime mt4 = new MeetingTime("MT4", "SUN 02:00 - 04:50");
        MeetingTime mt5 = new MeetingTime("MT5", "MON 08:30 - 09:50");
        MeetingTime mt6 = new MeetingTime("MT6", "MON 10:00 - 11:20");
        MeetingTime mt7 = new MeetingTime("MT7", "MON 11:30 - 12:50");
        MeetingTime mt8 = new MeetingTime("MT8", "MON 02:00 - 04:50"); 
        MeetingTime mt9 = new MeetingTime("MT9", "TUE 08:30 - 09:50");
        MeetingTime mt10 = new MeetingTime("MT10", "TUE 10:00 - 11:20");
        MeetingTime mt11 = new MeetingTime("MT11", "TUE 11:30 - 12:50");
        MeetingTime mt12 = new MeetingTime("MT12", "TUE 02:00 - 04:50");
        MeetingTime mt13 = new MeetingTime("MT13", "WED 08:30 - 09:50");
        MeetingTime mt14 = new MeetingTime("MT14", "WED 10:00 - 11:20");
        MeetingTime mt15 = new MeetingTime("MT15", "WED 11:30 - 12:50");
        MeetingTime mt16 = new MeetingTime("MT16", "WED 02:00 - 04:50");
        MeetingTime mt17 = new MeetingTime("MT17", "THU 08:30 - 09:50");
        MeetingTime mt18 = new MeetingTime("MT18", "THU 10:00 - 11:20");
        MeetingTime mt19 = new MeetingTime("MT19", "THU 11:30 - 12:50");
        MeetingTime mt20 = new MeetingTime("MT20", "THU 02:00 - 04:50");

        meetingtimes = new ArrayList<MeetingTime>(Arrays.asList(mt1, mt2, mt3, mt4, mt5,
                mt6, mt7, mt8, mt9, mt10, mt11, mt12, mt13, mt14, mt15, mt16, mt17, mt18, mt19, mt20));
        labmeetingtimes = new ArrayList<MeetingTime>(Arrays.asList(mt4,
                mt8,mt12,mt16,mt20));
        nonlabmeetingtimes = new ArrayList<MeetingTime>(Arrays.asList(mt1, mt2, mt3,mt5,
                mt6, mt7, mt9, mt10, mt11, mt13, mt14, mt15, mt17, mt18, mt19 ));

        courses = new ArrayList<Course>();
        instructors = new ArrayList<Instructor>();
        data.forEach(x -> {
            ArrayList<Instructor> templist = new ArrayList<>();
            templist.add(new Instructor(x.getTeacher_id(), x.getTeacher_name()));
            instructors.add(new Instructor(x.getTeacher_id(), x.getTeacher_name()));
            courses.add(new Course(x.getId(), x.getTitle(), templist, 0));
        });
        ArrayList<Instructor> temp = new ArrayList<Instructor>();
        for (int i = 0; i < instructors.size(); i++) {
            boolean Ok = false;
            for (int j = 0; j < temp.size(); j++) {
                if (instructors.get(i).getId().compareTo(temp.get(j).getId()) == 0) {
                    Ok = true;
                }
            }
            if (Ok == false) {
                temp.add(instructors.get(i));
            }
        }
        instructors.clear();
        instructors.addAll(temp);
        Department dept1 = new Department("IIT-JU", courses);

        depts = new ArrayList<Department>(Arrays.asList(dept1));
        depts.forEach(x -> numberOfClasses += x.getCourses().size());
        return this;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Department> getDepts() {
        return depts;
    }

    public ArrayList<MeetingTime> getMeetingtimes() {
        return meetingtimes;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public ArrayList<MeetingTime> getLabmeetingtimes() {
        return labmeetingtimes;
    }

    public ArrayList<MeetingTime> getNonlabmeetingtimes() {
        return nonlabmeetingtimes;
    }

    public Map<String, Double> getCoursecreditsmap() {
        return coursecreditsmap;
    }
    
}
