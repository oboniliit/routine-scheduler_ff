/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import DAO.Course;
import DAO.ShortRoutine;
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
public class ClsRoutineSceneFXMLController implements Initializable {

    @FXML
    private TableView<ShortRoutine> existingRoutine;
    private DBConnector handler = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;

    private String selectSQL = "select * from `routine` group by `uuid`";
    private String deleteSQL = "DELETE FROM `routine` WHERE `uuid`=?";
    private ObservableList<ShortRoutine> data;
    @FXML
    private TableColumn<ShortRoutine, Integer> order;
    @FXML
    private TableColumn<ShortRoutine, String> datetime;
    public static ShortRoutine clickedRoutineItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = DBConnector.getInstance();
        clickedRoutineItem = null;
        setCellfactory();
        loadDatafromDB();
    }

    @FXML
    private void onAddNewRoutine(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("ClassSceneFXML.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.showAndWait();
        loadDatafromDB();
    }

    @FXML
    private void onRemoveRoutine(ActionEvent event) {
        try {
            pre = handler.conn.prepareStatement(deleteSQL);
            pre.setString(1, clickedRoutineItem.getUuid());
            int var = pre.executeUpdate();
            pre.close();
            System.out.println("Successfully removed routine! "+var);
        } catch (SQLException e) {
            System.out.println(e);
        }
        loadDatafromDB();
    }

    private void loadDatafromDB() {
        data = FXCollections.observableArrayList();
        existingRoutine.setItems(null);
        int counter = 1;
        try {
            pre = handler.conn.prepareStatement(selectSQL);
            rs = pre.executeQuery();
            while (rs.next()) {
                data.add(new ShortRoutine(rs.getString(6), rs.getString(7), counter++));
            }
            pre.close();
            rs.close();
        } catch (SQLException e) {
        }
        existingRoutine.setItems(data);
    }

    private void setCellfactory() {
        order.setCellValueFactory(new PropertyValueFactory<>("order"));
        datetime.setCellValueFactory(new PropertyValueFactory<>("datetime"));
    }

    @FXML
    private void onRowClicked(MouseEvent event) throws IOException {
        clickedRoutineItem = existingRoutine.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            clickedRoutineItem = existingRoutine.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Parent root = FXMLLoader.load(getClass().getResource("SavedRoutineViewScene.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
    }
}
