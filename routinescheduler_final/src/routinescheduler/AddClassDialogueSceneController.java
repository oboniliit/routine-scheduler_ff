/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import DAO.AppClass;
import DAO.Course;
import DAO.Teacher;
import database.DBConnector;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import static routinescheduler.ClassSceneFXMLController.dataClass;

/**
 * FXML Controller class
 *
 * @author Farhin
 */
public class AddClassDialogueSceneController implements Initializable {

    DBConnector handler = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    String selectedCourseId = null;
    String selectCourseSQL = "SELECT * FROM course";
    String selectTeacherSQL = "SELECT * FROM teacher";

    @FXML
    private ComboBox<Course> course;
    @FXML
    private ComboBox<Teacher> teacher;
    @FXML
    private ComboBox<String> semester;
    @FXML
    private ComboBox<String> year;

    ObservableList<String> sem = FXCollections.observableArrayList("1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th");
    ObservableList<String> list = FXCollections.observableArrayList("2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040");
    ObservableList<Course> dataCourse;
    ObservableList<Teacher> dataTeacher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = DBConnector.getInstance();
        //semester.setItems(sem);
        //year.setItems(list);
        loadDatafromDB();
    }

    private void loadDatafromDB() {
        dataCourse = FXCollections.observableArrayList();
        dataTeacher = FXCollections.observableArrayList();
        course.setItems(null);
        teacher.setItems(null);
        try {
            pre = handler.conn.prepareStatement(selectTeacherSQL);
            rs = pre.executeQuery();
            while (rs.next()) {
                dataTeacher.add(new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            pre.close();
            rs.close();
            pre = handler.conn.prepareStatement(selectCourseSQL);
            rs = pre.executeQuery();
            while (rs.next()) {
                dataCourse.add(new Course(rs.getString(1), rs.getString(2), new Double(rs.getString(3))));
            }
            pre.close();
            rs.close();
        } catch (SQLException e) {
        }
        teacher.setItems(dataTeacher);
        course.setItems(dataCourse);
    }

    @FXML
    private void onOkay(ActionEvent event) {
        AppClass newClass = null;
        if (teacher.getSelectionModel().getSelectedItem() != null && course.getSelectionModel().getSelectedItem() != null) {
            newClass = new AppClass(course.getSelectionModel().getSelectedItem().getId(),
                    course.getSelectionModel().getSelectedItem().getTitle(),
                    teacher.getSelectionModel().getSelectedItem().getId(),
                    teacher.getSelectionModel().getSelectedItem().getName());
        }

        if (newClass != null) {
            System.out.println("I am executing!");
            dataClass.add(newClass);
        }
    }

}
