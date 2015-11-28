/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labscheduler;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

/**
 *
 * @author dpryor
 */
public class CheckAvaiController extends Stage implements Initializable{
    
    @FXML private TableView<Event> tbEvents;
    @FXML private TableView<Event> tbCurrentEvent;
    @FXML private Button btnCheckAvailability;
    @FXML private Label lbAvailability;
    @FXML private Button btnClose;
    private boolean availability;
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a");

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TableColumn<Event, String> cEventTitle = 
                new TableColumn<Event, String>("Title:");
        TableColumn<Event, String> cEventDate = 
                new TableColumn<Event, String>("Date:");
        TableColumn<Event, String> cStartTime = 
                new TableColumn<Event, String>("Start Time:");
        TableColumn<Event, String> cEndTime = 
                new TableColumn<Event, String>("End Time:");
        
        
        TableColumn<Event, String> eventTitle = 
                new TableColumn<Event, String>("Title:");
        TableColumn<Event, String> eventDate = 
                new TableColumn<Event, String>("Date:");
        TableColumn<Event, String> startTime = 
                new TableColumn<Event, String>("Start Time:");
        TableColumn<Event, String> endTime = 
                new TableColumn<Event, String>("End Time:");
        
        cEventTitle.setCellValueFactory( 
                new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() 
                    {
                        public ObservableValue<String> call(CellDataFeatures<Event, String> cellData) {
                        // cellData.getValue() returns the Person instance for a particular TableView row
                            return new SimpleStringProperty(cellData.getValue().getEventTitle());
                        }
                    }
        );
        cEventDate.setCellValueFactory( 
                new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() 
                    {
                        public ObservableValue<String> call(CellDataFeatures<Event, String> cellData) {
                        // cellData.getValue() returns the Person instance for a particular TableView row
                            return new SimpleStringProperty(cellData.getValue().getDate());
                        }
                    }
        );
        cStartTime.setCellValueFactory( 
                new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() 
                    {
                        public ObservableValue<String> call(CellDataFeatures<Event, String> cellData) {
                        // cellData.getValue() returns the Person instance for a particular TableView row
                            return new SimpleStringProperty(cellData.getValue().getStartTime());
                        }
                    }
        );
        cEndTime.setCellValueFactory( 
                new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() 
                    {
                        public ObservableValue<String> call(CellDataFeatures<Event, String> cellData) {
                        // cellData.getValue() returns the Person instance for a particular TableView row
                            return new SimpleStringProperty(cellData.getValue().getEndTime());
                        }
                    }
        );
        
        eventTitle.setCellValueFactory( 
                new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() 
                    {
                        public ObservableValue<String> call(CellDataFeatures<Event, String> cellData) {
                        // cellData.getValue() returns the Person instance for a particular TableView row
                            return new SimpleStringProperty(cellData.getValue().getEventTitle());
                        }
                    }
        );
        eventDate.setCellValueFactory( 
                new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() 
                    {
                        public ObservableValue<String> call(CellDataFeatures<Event, String> cellData) {
                        // cellData.getValue() returns the Person instance for a particular TableView row
                            return new SimpleStringProperty(cellData.getValue().getDate());
                        }
                    }
        );
        startTime.setCellValueFactory( 
                new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() 
                    {
                        public ObservableValue<String> call(CellDataFeatures<Event, String> cellData) {
                        // cellData.getValue() returns the Person instance for a particular TableView row
                            return new SimpleStringProperty(cellData.getValue().getStartTime());
                        }
                    }
        );
        endTime.setCellValueFactory( 
                new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() 
                    {
                        public ObservableValue<String> call(CellDataFeatures<Event, String> cellData) {
                        // cellData.getValue() returns the Person instance for a particular TableView row
                            return new SimpleStringProperty(cellData.getValue().getEndTime());
                        }
                    }
        );
        
        ObservableList<Event> data = FXCollections.observableArrayList(LabScheduler.eventCollection.allEvents);
        ObservableList<Event> cdata = FXCollections.observableArrayList(LabScheduler.event);
        
        endTime.setMinWidth(142);
        startTime.setMinWidth(135);
        eventTitle.setMinWidth(150);
        eventDate.setMinWidth(135);
        cEndTime.setMinWidth(142);
        cStartTime.setMinWidth(135);
        cEventTitle.setMinWidth(150);
        cEventDate.setMinWidth(135);
        tbCurrentEvent.setMaxHeight(50);
        tbCurrentEvent.getColumns().addAll(cEventTitle, cEventDate, cStartTime, cEndTime);
        tbCurrentEvent.setItems(cdata);
        
        tbEvents.getColumns().addAll(eventTitle, eventDate, startTime, endTime);
        tbEvents.setItems(data);
    }

    @FXML
    private void onCheckAvailability(ActionEvent event) {
        for(Event obj : LabScheduler.eventCollection.allEvents){
            if(obj.getDate().equals(LabScheduler.event.getDate())){
                try {
                    if((dateFormat.parse(LabScheduler.event.getStartTime()).after(dateFormat.parse(obj.getStartTime())) || (LabScheduler.event.getStartTime().equals(obj.getStartTime())))  && 
                            dateFormat.parse(LabScheduler.event.getStartTime()).before(dateFormat.parse(obj.getEndTime()))){
                        availability = false;
                    }
                    else if (dateFormat.parse(LabScheduler.event.getEndTime()).after(dateFormat.parse(obj.getStartTime())) && 
                            ((dateFormat.parse(LabScheduler.event.getEndTime()).before(dateFormat.parse(obj.getEndTime()))) || (LabScheduler.event.getEndTime().equals(obj.getEndTime())))){
                        availability = false;
                    }
                    else{
                        availability = true;
                    }
                 }
                catch(ParseException ex){
                    // ToDO                    
                }
            }
            else {
                availability = true;
            }
        }
        if(availability){
            lbAvailability.setText("Lab available on " + LabScheduler.event.getDate());
            LabScheduler.availability = true;
        }
        else{
            lbAvailability.setText("Lab not available on " + LabScheduler.event.getDate());
            LabScheduler.availability = false;
        }
    }

    @FXML
    private void onClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
