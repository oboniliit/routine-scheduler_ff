/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import DAO.Course;
import database.DBConnector;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ddxofy
 */
public class CourseSceneFXMLController implements Initializable {

    DBConnector handler = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    String selectedCourseId = null;
    String selectSQL = "SELECT * FROM course ORDER BY course_id";
    String removeSQL = "DELETE FROM course WHERE course_id=?";

    private ObservableList<Course> data;

    @FXML
    private TableView<Course> courseTableView;
    @FXML
    private TableColumn<Course, String> id;
    @FXML
    private TableColumn<Course, String> title;
    @FXML
    private TableColumn<Course, Double> credit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DBConnector.getInstance();
        setCellfactory();
        loadDatafromDB();
    }

    private void setCellfactory() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
    }

    private void loadDatafromDB() {
        data = FXCollections.observableArrayList();
        courseTableView.setItems(null);
        try {
            pre = handler.conn.prepareStatement(selectSQL);
            rs = pre.executeQuery();
            while (rs.next()) {
                data.add(new Course(rs.getString(1), rs.getString(2), new Double(rs.getString(3))));
            }
            pre.close();
            rs.close();
        } catch (SQLException e) {
        }
        courseTableView.setItems(data);
    }

    @FXML
    private void onMousePressedAction(MouseEvent event) {
        selectedCourseId = courseTableView.getSelectionModel().getSelectedItem().getId();
    }

    @FXML
    private void onRemoveCourse(ActionEvent event) {
        try {
            pre = handler.conn.prepareStatement(removeSQL);
            pre.setString(1, selectedCourseId);
            pre.executeUpdate();
            System.out.println("Course Successfully removed!");
            pre.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        loadDatafromDB();
    }

    @FXML
    private void onAddCourse(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("AddCourseDialougeScene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.showAndWait();
        loadDatafromDB();
    }

}
