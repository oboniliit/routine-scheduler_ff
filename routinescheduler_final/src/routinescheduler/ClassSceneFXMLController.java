/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import DAO.AppClass;
import com.project.gal.cs.Driver;
import com.project.gal.cs.Schedule;
import com.project.gal.cs.models.Course;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class ClassSceneFXMLController implements Initializable {
    public static Schedule generatedRoutine;
    @FXML
    private TableView<AppClass> ClassListView;
    public static ObservableList<AppClass> dataClass;
    @FXML
    private TableColumn<AppClass, String> teacherName;
    @FXML
    private TableColumn<AppClass, String> courseId;
    private AppClass selectedItem;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectedItem = null;
        setCellFactoryValue();
        dataClass = FXCollections.observableArrayList();
        ClassListView.setItems(dataClass);
    }    

    @FXML
    private void onRowSelect(MouseEvent event) {
        selectedItem = ClassListView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void onAddClass(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("AddClassDialogueScene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onRemoveClass(ActionEvent event) {
        if(selectedItem!=null)
        {
            dataClass.remove(selectedItem);
        }
    }

    @FXML
    private void onGenerateRoutine(ActionEvent event) throws IOException {
        ArrayList<AppClass> templ = new ArrayList<AppClass>();
        dataClass.forEach(x -> templ.add(x));
        Driver driver = new Driver(templ);
        generatedRoutine = driver.runAI();
        
        System.out.println("You clicked me!");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("RoutineViewScene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
    }
    private void setCellFactoryValue(){
        courseId.setCellValueFactory(new PropertyValueFactory<>("id"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("teacher_name"));
    }
    
}
