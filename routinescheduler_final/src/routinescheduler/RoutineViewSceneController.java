/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import DAO.RoutineClass;
import database.DBConnector;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Farhin
 */
public class RoutineViewSceneController implements Initializable {

    @FXML
    private TableView< RoutineClass> routineView;
    @FXML
    private TableColumn<RoutineClass, String> teacherid;
    @FXML
    private TableColumn<RoutineClass, String> room;
    @FXML
    private TableColumn<RoutineClass, String> time;
    @FXML
    private TableColumn<RoutineClass, String> courseid;
    @FXML
    private TableColumn<RoutineClass, Integer> rownumber;

    private ObservableList<RoutineClass> data;

    private Integer counter;

    private String uuid;

    private boolean isLoadingfromDB;

    private DBConnector handler = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;

    String insertSQL = "INSERT INTO `routine` (`courseid`, `teacherid`, `roomid`, `classtime`, `classorder`, `uuid`, `saveddate`) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = DBConnector.getInstance();
        data = FXCollections.observableArrayList();
        routineView.setItems(null);
        isLoadingfromDB = false;
        setCellFactoryValue();
        if (isLoadingfromDB == false) {
            counter = 1;
            uuid = UUID.randomUUID().toString();
            ClassSceneFXMLController.generatedRoutine.getClasses().forEach(x -> {
                data.add(new RoutineClass(x.getCourse().getNumber(), x.getInstructor().getId(),
                        x.getRoom().getNumber(), x.getMeetingtime().getTime(), counter++, uuid));
            });
            routineView.setItems(data);
        }
    }

    private void setCellFactoryValue() {
        courseid.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        teacherid.setCellValueFactory(new PropertyValueFactory<>("teacherid"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        rownumber.setCellValueFactory(new PropertyValueFactory<>("order"));
    }

    @FXML
    private void onSwapTimeButtonClicked(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Swap Time Schedule");
        dialog.setHeaderText("Please! Give space separated two row numbers.");
        String rownumbers[] = null;
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            rownumbers = result.get().split(" ");
        }
        int row1 = 0, row2 = 0;
        try {
            row1 = Integer.parseInt(rownumbers[0]);
            row2 = Integer.parseInt(rownumbers[1]);
        } catch (Exception EX) {
            System.err.println(EX);
        }

        if (row1 > 0 && row2 > 0) {
            String temptime = data.get(row1 - 1).getTime();
            data.get(row1 - 1).setTime(data.get(row2 - 1).getTime());
            data.get(row2 - 1).setTime(temptime);
        }
    }

    @FXML
    private void onSaveButtonClicked(ActionEvent event) {
        Timestamp date = new Timestamp(new Date().getTime());
        try {
            pre = handler.conn.prepareStatement(insertSQL);
            for (RoutineClass routineClass : data) {

                pre.setString(1, routineClass.getCourseid());
                pre.setString(2, routineClass.getTeacherid());
                pre.setString(3, routineClass.getRoom());
                pre.setString(4, routineClass.getTime());
                pre.setInt(5, routineClass.getOrder());
                pre.setString(6, routineClass.getId());
                pre.setTimestamp(7, date);
                pre.executeUpdate();
                System.out.println(routineClass.getCourseid() + " Successfully Inserted into Database.");

            }
            pre.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        Stage stage = (Stage) routineView.getScene().getWindow();
        stage.close();
    }

}
