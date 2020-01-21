/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinescheduler;

import DAO.RoutineClass;
import database.DBConnector;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

//itext libraries to write PDF file
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Farhin
 */
public class SavedRoutineViewSceneController implements Initializable {

    @FXML
    private TableView<RoutineClass> routineView;
    @FXML
    private TableColumn<RoutineClass, Integer> rownumber;
    @FXML
    private TableColumn<RoutineClass, String> courseid;
    @FXML
    private TableColumn<RoutineClass, String> teacherid;
    @FXML
    private TableColumn<RoutineClass, String> room;
    @FXML
    private TableColumn<RoutineClass, String> time;

    private ObservableList<RoutineClass> data;

    private DBConnector handler = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;

    private String selectSQL = "SELECT * FROM `routine` WHERE `uuid`=?";
    @FXML
    private TextField searchTextfield;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = DBConnector.getInstance();
        setCellFactoryValue();
        loadDatafromDB();
    }

    private void setCellFactoryValue() {
        courseid.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        teacherid.setCellValueFactory(new PropertyValueFactory<>("teacherid"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        rownumber.setCellValueFactory(new PropertyValueFactory<>("order"));
    }

    private void loadDatafromDB() {
        data = FXCollections.observableArrayList();
        routineView.setItems(null);
        int counter = 1;
        try {
            pre = handler.conn.prepareStatement(selectSQL);
            pre.setString(1, ClsRoutineSceneFXMLController.clickedRoutineItem.getUuid());
            rs = pre.executeQuery();
            while (rs.next()) {
                data.add(new RoutineClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
            pre.close();
            rs.close();
        } catch (SQLException e) {
        }
        routineView.setItems(data);
    }

    @FXML
    private void fireSearch(KeyEvent event) {
        if (searchTextfield.textProperty().get().isEmpty()) {
            routineView.setItems(data);
            return;
        }
        ObservableList<RoutineClass> tableItems = FXCollections.observableArrayList();
        ObservableList<TableColumn<RoutineClass, ?>> cols = routineView.getColumns();
        for (int i = 0; i < data.size(); i++) {

            for (int j = 0; j < cols.size(); j++) {
                TableColumn col = cols.get(j);
                String cellValue = col.getCellData(data.get(i)).toString();
                cellValue = cellValue.toLowerCase();
                if (cellValue.contains(searchTextfield.textProperty().get().toLowerCase())) {
                    tableItems.add(data.get(i));
                    break;
                }
            }
        }
        routineView.setItems(tableItems);
    }

    @FXML
    private void printToPDFfile(ActionEvent event) {
        Document iText_pdf = new Document();
        try {
            try {
                FileOutputStream files = new FileOutputStream("Generated Routine.pdf");
                PdfWriter.getInstance(iText_pdf, files);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SavedRoutineViewSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(SavedRoutineViewSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        iText_pdf.open();
        //we have two columns in the Excel sheet, so we create a PDF table with two columns
        //Note: There are ways to make this dynamic in nature, if you want to.
        PdfPTable my_table = new PdfPTable(5);
        //We will use the object below to dynamically add new data to the table
        PdfPCell table_cell;
        //Loop through rows.

        ObservableList<TableColumn<RoutineClass, ?>> cols = routineView.getColumns();
        int numberOfColoumns = cols.size();
        for (int j = 0; j < numberOfColoumns; j++) {
            TableColumn col = cols.get(j);
            table_cell = new PdfPCell(new Phrase(col.getText()));
            my_table.addCell(table_cell);
        }

        for (int i = 0; i < data.size(); i++) {
            table_cell = new PdfPCell(new Phrase(data.get(i).getOrder().toString()));
            my_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(data.get(i).getCourseid()));
            my_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(data.get(i).getTeacherid()));
            my_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(data.get(i).getRoom()));
            my_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(data.get(i).getTime()));
            my_table.addCell(table_cell);
        }
        try {
            //Finally add the table to PDF document
            iText_pdf.add(my_table);
        } catch (DocumentException ex) {
            Logger.getLogger(SavedRoutineViewSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        iText_pdf.close();
        Alert alert = new Alert(AlertType.INFORMATION, "Generated Routine.pdf is created in current directory!", ButtonType.OK);
        alert.showAndWait();
    }
}
