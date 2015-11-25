/*
 * 
 * ***** Security Issues ***********
 * Need to Sanitize Input
 * 
 * ***** Event Interaction *********
 * Get Date from user
 * Validate date from user
 * * Clear data from user
 * Put date into datasource(txt, file)
 */

package labscheduler;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.scene.control.ToggleGroup; 
import java.text.SimpleDateFormat;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;




/**
 *
 * @author dpryor
 */
public class LabSchedulerFXMLController implements Initializable {
    @FXML private Label lblBanner;
    @FXML private Label lblRequestorName;
    @FXML private Label lblRequestorEmail;
    @FXML private Label lblEventTitle;
    @FXML private Label lblNumofParticipants;
    @FXML private Label lblStartTime;
    @FXML private Label lblDate;
    @FXML private Label lblEndTime;
    @FXML private Label lblPrinterRequest;
    @FXML private Label lblSpecialSoftwareRequests;
    @FXML private TextArea txtSpecialSoftwareRequests;
    @FXML private TextField txtRequestorName;
    @FXML private TextField txtRequestorEmail;
    @FXML private TextField txtEventTitle;
    @FXML private TextField txtNumOfParticipants;
    @FXML private TextField txtDate;
    @FXML private TextField txtStartTime;
    @FXML private TextField txtEndTime;
    @FXML private CheckBox cboxPrinterRequest;
    @FXML private Button btnSubmitLabRequest;
    @FXML private Button btnClear;
    @FXML private Button btnExit;
    @FXML private ToggleButton togStartMeridiem;
    @FXML private ToggleButton togEndMeridiem;
    @FXML private RadioButton radYes;
    @FXML private ToggleGroup SpecialSoftwareRequest;
    @FXML private RadioButton radNo;
    @FXML private Button btnApptRecurrence;
    @FXML public Button btnCheckAvailability;
    
       
    // Variables and Stuff
    public static CheckAvaiController checkAvailabilityStage;
    public static ApptReFXMLController apptReStage;    
    boolean specialSoftwareRequest = false;
    boolean startMeridiem = false;
    boolean endMeridiem = false;
    boolean printerRequest = false;
    String errorMessage = "";
      
    public void handleExit(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }
    //Clear all data from existing submit
    public void handleClear(ActionEvent event){
        //todo
    }
    
    //Set the SSR to enabled and editable
    public void handleGetSSRToggleYes(ActionEvent event){
        txtSpecialSoftwareRequests.setEditable(true);
        txtSpecialSoftwareRequests.setDisable(false);
        specialSoftwareRequest = true; 
    }
    
    //Set the SSR to disabled and uneditable
    public void handleGetSSRToggleNo(ActionEvent event){
        txtSpecialSoftwareRequests.setEditable(false);
        txtSpecialSoftwareRequests.setDisable(true);
        txtSpecialSoftwareRequests.setText(null);
        specialSoftwareRequest = false;        
    }
    
    //Set The Printer Request variable
    public void handlePrinterRequest(ActionEvent event){
        if(cboxPrinterRequest.isSelected()){
            printerRequest = true; 
        }
        else{
            printerRequest = false; 
        }
    }
    
    //Set End Meridiem Text
    public void handleStartMeridiem(ActionEvent event){
        if(togStartMeridiem.isSelected()){
            togStartMeridiem.setText("PM");
            startMeridiem = true; 
        }
        else {
            togStartMeridiem.setText("AM");
            startMeridiem = false;
        }
    }
    //Set End Meridiem Text
    public void handleEndMeridiem(ActionEvent event){
        if(togEndMeridiem.isSelected()){
            togEndMeridiem.setText("PM");
            endMeridiem = true; 
        }
        else {
            togEndMeridiem.setText("AM");
            endMeridiem = false;
        }
    }
    
    public void handleSubmitLabRequest(ActionEvent event) {
        // Variables
        String requestorName = "";
        String requestorEmail = ""; 
        String eventTitle = "";
        Integer numOfParticipants = 0;
        String specialSoftwareRequests = "";
        String date = "";
        String startTime = "";
        String endTime = "";
        
        
        //Outside try catch to anything
        try{
            // Validate requestor Name
            try {
                requestorName = txtRequestorName.getText();
                if(requestorName.equals("")){
                    throw new Exception("Please enter a Name!\n");
                }
                txtRequestorName.setBorder(Border.EMPTY);
            }
            catch(Exception ex){
                txtRequestorName.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += ex.getMessage();
            }

            //Validate requestorEmail 
            try{
                requestorEmail = txtRequestorEmail.getText();
                // RegEx provided by http://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
                if(!requestorEmail.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")){
                    throw new Exception("Please enter a valid email.\n");
                }
                txtRequestorEmail.setBorder(Border.EMPTY);
            }
            catch(Exception ex){
                txtRequestorEmail.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += ex.getMessage();
            }

            //Validate eventTitle
            try {
                eventTitle = txtEventTitle.getText();
                if(eventTitle.equals("")){
                    throw new Exception("Please enter a title!\n");
                }
                txtEventTitle.setBorder(Border.EMPTY);
            }
            catch(Exception ex){
                txtEventTitle.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += ex.getMessage();
            }


            // Validate numOfParticants
            try {
                numOfParticipants = Integer.parseInt(txtNumOfParticipants.getText());
                if (numOfParticipants <= 0){
                    throw new NumberFormatException("Number of Participants is less then or equal to 0.\n");      
                }
                txtNumOfParticipants.setBorder(Border.EMPTY);
            }
            catch(NumberFormatException ex){
                txtNumOfParticipants.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                String message = ex.getMessage();
                if(message.contains("input")){
                    message = "Please validate the Number of Participants!\n";
                }
                errorMessage += message;
            }

            //Validate that txtSpecialSoftwareRequest has data
            if(specialSoftwareRequest){
                try {
                    specialSoftwareRequests = txtSpecialSoftwareRequests.getText();
                    if(specialSoftwareRequests.equals("")){
                        throw new Exception("Since you checked yes to Special Software Requests.. Please add some software or click no.");
                    }
                }
                catch(Exception ex){
                    errorMessage += ex.getMessage();
                }
            }
            
            //Validate Date format and date
            try {
                date = txtDate.getText();
                SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
                sdfrmt.setLenient(false);
                if(!sdfrmt.parse(date).after(new Date())){
                    throw new Exception();
                }
                txtDate.setBorder(Border.EMPTY);
            }
            catch(Exception ex){
                txtDate.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += "Please enter a valid date!\n";
            }
            
            // Validate Start Time
            try {
                startTime = txtStartTime.getText();
                if(!startTime.matches("(0[1-9]|1[012]|[1-9]):[0-5][0-9]")){
                    throw new Exception("Please enter a valid start time!\n");
                }
                txtStartTime.setBorder(Border.EMPTY);
            }
            catch(Exception ex){
                txtStartTime.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += ex.getMessage();
            }
            // Validate End Time
            try {
                endTime = txtEndTime.getText();
                if(!endTime.matches("(0[1-9]|1[012]|[1-9]):[0-5][0-9]")){
                    throw new Exception("Please enter a valid end time!\n");
                }
                txtEndTime.setBorder(Border.EMPTY);
            }
            catch(Exception ex){
                txtEndTime.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += ex.getMessage();
            }
            
            //Validate time is after start time.
            if(!startTime.isEmpty() || !endTime.isEmpty()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                if(dateFormat.parse(startTime).after(dateFormat.parse(endTime)) && endMeridiem != true){
                    errorMessage += "Start Time is after End Time.";
                }
                if(dateFormat.parse(startTime).after(dateFormat.parse(endTime)) && endMeridiem == true && startMeridiem == true){
                    errorMessage += "Start Time is after End Time.";
                }
                if(startTime.equals(endTime) && endMeridiem == startMeridiem){
                    errorMessage += "Start Time is equal to End Time.";
                }
            }

            
/*
        @FXML private Button btnClear;
**/
  
            // Check if error ruffer is clear and continue if it is. 
            if (!errorMessage.equals("")){
                throw new Exception(errorMessage);
            }
            // Pop up window to show output data
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "RequestorName = " + requestorName + "\nrequestorEmail = " +  requestorEmail +
                    "\neventTitle = " +  eventTitle +  "\nnumOfParticipants = " + numOfParticipants + "\nspecialSoftwareRequests = " + specialSoftwareRequests + 
                    "\nDate = " + date + "\nstartTime = " + startTime + togStartMeridiem.getText() + "\nendTime = " + endTime + togEndMeridiem.getText());
            Optional<ButtonType> result = alert.showAndWait();

        }
        catch(Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                Optional<ButtonType> result = alert.showAndWait();
            errorMessage = "";
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkAvailabilityStage = new CheckAvaiController();
        btnCheckAvailability.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("CheckAvaiFXML.fxml"));
                    Scene scene = new Scene(root);
 
                    checkAvailabilityStage.setScene(scene);
                    checkAvailabilityStage.show();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        apptReStage = new ApptReFXMLController();
        btnApptRecurrence.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("ApptReFXML.fxml"));
                    Scene scene = new Scene(root);
 
                    apptReStage.setScene(scene);                    
                    apptReStage.showAndWait();
                    
                    if (LabScheduler.fieldsdisabled){
                        btnCheckAvailability.setDisable(LabScheduler.fieldsdisabled);
                        txtDate.setDisable(LabScheduler.fieldsdisabled);
                        txtStartTime.setDisable(LabScheduler.fieldsdisabled);
                        txtEndTime.setDisable(LabScheduler.fieldsdisabled);
                        togStartMeridiem.setDisable(LabScheduler.fieldsdisabled);
                        togEndMeridiem.setDisable(LabScheduler.fieldsdisabled);
                        txtDate.clear();
                        txtStartTime.clear();
                        txtEndTime.clear();
                        
                    }
                    else {
                        btnCheckAvailability.setDisable(LabScheduler.fieldsdisabled);
                        txtDate.setDisable(LabScheduler.fieldsdisabled);
                        txtStartTime.setDisable(LabScheduler.fieldsdisabled);
                        txtEndTime.setDisable(LabScheduler.fieldsdisabled);
                        togStartMeridiem.setDisable(LabScheduler.fieldsdisabled);
                        togEndMeridiem.setDisable(LabScheduler.fieldsdisabled);
                    }
                    
                } catch (Exception ex) {
                    
                }
            }
        });
    }    
}
