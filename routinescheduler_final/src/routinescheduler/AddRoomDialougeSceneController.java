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
public class AddRoomDialougeSceneController implements Initializable {

    DBConnector handler = null;
    PreparedStatement pre = null;
    String insertSQL = "INSERT INTO room VALUES (? , ? , ? ,?)";

    @FXML
    private TextField no;
    @FXML
    private TextField floor;
    @FXML
    private TextField type;
    @FXML
    private TextField capacity;

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
        System.out.println(no.getText() + " - " + floor.getText() + " - " + type.getText() + " - " + capacity.getText());
        try {
            pre = handler.conn.prepareStatement(insertSQL);
            pre.setString(1, no.getText());
            pre.setInt(2, new Integer(floor.getText()));
            pre.setString(3, type.getText());
            pre.setInt(4, new Integer(capacity.getText()));
            pre.executeUpdate();
            System.err.println("Succesfully added!");
        } catch (Exception e) {
        }
        Stage stage = (Stage) no.getScene().getWindow();
        stage.close();
    }

}
