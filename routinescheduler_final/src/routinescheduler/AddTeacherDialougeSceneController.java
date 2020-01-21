/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import database.DBConnector;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Farhin
 */
public class AddTeacherDialougeSceneController implements Initializable {
    
    DBConnector handler = null;
    PreparedStatement pre = null;
    String insertSQL = "INSERT INTO teacher VALUES (? , ? , ? , ? , ?)";

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField designation;
    @FXML
    private TextField department;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = DBConnector.getInstance();
    }    

    @FXML
    private void onOk(ActionEvent event) {
         System.out.println(id.getText() + " - " + name.getText() + " - " + designation.getText() + " - " + department.getText() + " - " + email.getText());
        try {
            pre = handler.conn.prepareStatement(insertSQL);
            pre.setString(1, id.getText());
            pre.setString(2, name.getText());
            pre.setString(3, designation.getText());
            pre.setString(4, department.getText());
            pre.setString(5, email.getText());
            pre.executeUpdate();
            System.err.println("Succesfully added!");
        } catch (Exception e) {
        }
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
    
}
