/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import DAO.Room;
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
public class RoomSceneFXMLController implements Initializable {

    DBConnector handler = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    String selectedRoomNo = null;
    String selectSQL = "SELECT * FROM room";
    String removeSQL = "DELETE FROM room WHERE room_no=?";

    private ObservableList<Room> data;

    @FXML
    private TableView< Room> roomlistview;
    @FXML
    private TableColumn<Room, String> no;
    @FXML
    private TableColumn<Room, Integer> floor;
    @FXML
    private TableColumn<Room, String> type;
    @FXML
    private TableColumn<Room, Integer> capacity;

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

    @FXML
    private void onRowSelect(MouseEvent event) {
        selectedRoomNo = roomlistview.getSelectionModel().getSelectedItem().getNo();
    }

    @FXML
    private void onAddRoom(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("AddRoomDialougeScene.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.showAndWait();
        loadDatafromDB();
    }

    @FXML
    private void onRemoveRoom(ActionEvent event) {
        try {
            pre = handler.conn.prepareStatement(removeSQL);
            pre.setString(1, selectedRoomNo);
            pre.executeUpdate();
            System.out.println("Room Successfully removed!");
            pre.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        loadDatafromDB();
    }

    private void setCellfactory() {
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

    }

    private void loadDatafromDB() {
        data = FXCollections.observableArrayList();
        roomlistview.setItems(null);
        try {
            pre = handler.conn.prepareStatement(selectSQL);
            rs = pre.executeQuery();
            while (rs.next()) {
                data.add(new Room(rs.getString(1), new Integer(rs.getString(2)), rs.getString(3), new Integer(rs.getString(4))));
            }
            pre.close();
            rs.close();
        } catch (SQLException e) {
        }
        roomlistview.setItems(data);

    }

}
