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
 * @author ddxofy
 */
public class AddCourseDialougeSceneController implements Initializable {

    DBConnector handler = null;
    PreparedStatement pre = null;
    String insertSQL = "INSERT INTO course VALUES (? , ? , ?)";
    
    
    @FXML
    private TextField id;
    @FXML
    private TextField title;
    @FXML
    private TextField credit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DBConnector.getInstance();
// TODO
    }

    @FXML
    private void onOkay(ActionEvent event) {
        System.out.println(id.getText() + " - " + title.getText() + " - " + credit.getText());
        try {
            pre = handler.conn.prepareStatement(insertSQL);
            pre.setString(1, id.getText());
            pre.setString(2, title.getText());
            pre.setDouble(3, new Double(credit.getText()));
            pre.executeUpdate();
            System.err.println("Succesfully added!");
        } catch (Exception e) {
        }
        Stage stage = (Stage) title.getScene().getWindow();
        stage.close();
    }

}
