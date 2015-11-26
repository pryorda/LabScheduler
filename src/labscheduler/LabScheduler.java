/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labscheduler;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author dpryor
 */
public class LabScheduler extends Application {
    public static boolean fieldsdisabled;
    public static EventCollection eventCollection;
    public static Event event; 
        
    @Override
    public void start(Stage stage) throws Exception {
        
        eventCollection = new EventCollection();
        eventCollection.readFile();
        event = new Event();
                
        Parent root = FXMLLoader.load(getClass().getResource("LabSchedulerFXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
