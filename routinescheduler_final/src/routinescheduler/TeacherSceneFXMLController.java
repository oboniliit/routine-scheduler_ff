/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import DAO.Teacher;
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
 * @author Farhin
 */
public class TeacherSceneFXMLController implements Initializable {
    DBConnector handler = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    String selectedTeacherId = null;
    String selectSQL = "SELECT * FROM teacher ORDER BY teacher_id";
    String removeSQL = "DELETE FROM teacher WHERE teacher_id=?";

    private ObservableList<Teacher> data;

    @FXML
    private TableView<Teacher> teachertableview;
    @FXML
    private TableColumn<Teacher, String> id;
    @FXML
    private TableColumn<Teacher, String> name;
    @FXML
    private TableColumn<Teacher, String> designation;
    @FXML
    private TableColumn<Teacher, String> department;
    @FXML
    private TableColumn<Teacher, String> email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = DBConnector.getInstance();
        setCellfactory();
        loadDatafromDB();
    }    
    private void setCellfactory() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }   
    private void loadDatafromDB() {
        data = FXCollections.observableArrayList();
        teachertableview.setItems(null);
        try {
            pre = handler.conn.prepareStatement(selectSQL);
            rs = pre.executeQuery();
            while (rs.next()) {
                data.add(new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            pre.close();
            rs.close();
        } catch (SQLException e) {
        }
        teachertableview.setItems(data);
    }


    @FXML
    private void onRowSelect(MouseEvent event) {
        selectedTeacherId = teachertableview.getSelectionModel().getSelectedItem().getId();
    }

    @FXML
    private void onAddTeacher(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("AddTeacherDialougeScene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.showAndWait();
        loadDatafromDB();
    }

    @FXML
    private void onRemoveTeacher(ActionEvent event) {
        try {
            pre = handler.conn.prepareStatement(removeSQL);
            pre.setString(1, selectedTeacherId);
            pre.executeUpdate();
            System.out.println("Teacher Successfully removed!");
            pre.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        loadDatafromDB();
    }
    
}
