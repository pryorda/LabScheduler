/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labscheduler;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<Event, String> eventTitle = 
                new TableColumn<Event, String>("Title:");
        TableColumn<Event, String> eventDate = 
                new TableColumn<Event, String>("Date:");
        TableColumn<Event, String> startTime = 
                new TableColumn<Event, String>("Start Time:");
        TableColumn<Event, String> endTime = 
                new TableColumn<Event, String>("End Time:");
        
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
        
        ObservableList<Event> data = FXCollections.observableArrayList(LabScheduler.eventCollection);
        
        tbEvents.getColumns().addAll(eventTitle, eventDate, startTime, endTime);
        tbEvents.setItems(data);
    }
}
