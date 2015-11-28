/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labscheduler;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 *
 * @author dpryor
 */
public class ApptReFXMLController extends Stage implements Initializable{
    
    @FXML private TextField txtStartTime;
    @FXML private ToggleButton togStartMeridiem;
    @FXML private ToggleButton togEndMeridiem;
    @FXML private TextField txtEndTime;
    @FXML private TextField txtBeginDate;
    @FXML private TextField txtEndDate;
    @FXML private Button btnOk;
    @FXML private Button btnCancel;
    @FXML private CheckBox chkSun;
    @FXML private CheckBox chkMon;
    @FXML private CheckBox chkTues;
    @FXML private CheckBox chkWed;
    @FXML private CheckBox chkThurs;
    @FXML private CheckBox chkFri;
    @FXML private CheckBox chkSat;    
    
    private String startTime;
    private String endTime;
    private String beginDate;
    private String endDate;
    private String errorMessage;
    private Boolean startMeridiem;
    private Boolean endMeridiem;
        
    public boolean validateAndSet(){
        errorMessage = "";
        //Outside try catch to anything
        try{            
            //Validate Date format and date
            try {
                endDate = txtEndDate.getText();
                beginDate = txtBeginDate.getText();                              
                
                System.out.println(txtBeginDate.getText());
                System.out.println(txtEndDate.getText());
                SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
                sdfrmt.setLenient(false);
                Date beginDateObj = sdfrmt.parse(beginDate);
                Date endDateObj = sdfrmt.parse(endDate);
                if(beginDate.isEmpty() || endDate.isEmpty()){
                    throw new Exception("Dates are not set!");
                }
                else if(!beginDateObj.after(new Date())){
                    throw new Exception("The beginning date has to be after todays date!");
                }
                else if(endDateObj.before(beginDateObj)){
                    throw new Exception("The ending date can not be before beginning date!");
                }
                else if(endDateObj.equals(beginDateObj)){
                    throw new Exception("Please click cancel to enter a single envent or fix your date selection!");
                }
                txtEndDate.setBorder(Border.EMPTY);
                txtBeginDate.setBorder(Border.EMPTY);
            }
            catch(Exception ex){
                txtEndDate.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                txtBeginDate.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += ex.getMessage();
                errorMessage += "\nValidate your date selection!";
            }
            
            // Validate Start Time
            try {
                startTime = txtStartTime.getText() + " " + togStartMeridiem.getText();
                if(!startTime.matches("(0[1-9]|1[012]|[1-9]):[0-5][0-9] [AP][M]")){
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
                endTime = txtEndTime.getText() + " " + togEndMeridiem.getText();
                if(!endTime.matches("(0[1-9]|1[012]|[1-9]):[0-5][0-9] [AP][M]")){
                    throw new Exception("Please enter a valid end time!\n");
                }
                txtEndTime.setBorder(Border.EMPTY);
            }
            catch(Exception ex){
                txtEndTime.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += ex.getMessage();
            }
            
            //Validate end time is after start time.
            try {
                if(!startTime.isEmpty() || !endTime.isEmpty()){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a");
                    if(dateFormat.parse(startTime).after(dateFormat.parse(endTime))){
                        throw new RuntimeException("\nStart Time is after End Time.");
                    }
                    if(startTime.equals(endTime)){
                        throw new RuntimeException("\nStart Time is equal to End Time.");
                    }
                }
            }
            catch(RuntimeException ex){
                txtEndTime.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                txtStartTime.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                errorMessage += ex.getMessage();
            
            }
            catch(Exception ex){
                txtEndTime.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
                txtStartTime.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
            }
            try {
                if( !chkSun.isSelected() && !chkMon.isSelected() && !chkTues.isSelected()  && !chkWed.isSelected() && !chkThurs.isSelected() && !chkFri.isSelected() && !chkSat.isSelected() ){
                    throw new RuntimeException("\nCheck at least one day.");
                }
            }
            catch (RuntimeException ex) {
                errorMessage += ex.getMessage();
                
            }

            // Check if error ruffer is clear and continue if it is. 
            if (!errorMessage.equals("")){
                throw new Exception(errorMessage);
            }
            
            LabScheduler.recurrence = "Occurs every ";
            
            if(chkSun.isSelected()){
                LabScheduler.recurrence += "Sun, ";
            }
            if(chkMon.isSelected()){
                LabScheduler.recurrence +=  "Mon, ";
            }
            if(chkTues.isSelected()){
                LabScheduler.recurrence +=  "Tues, ";
            }
            if(chkWed.isSelected()){
                LabScheduler.recurrence +=  "Wed, ";
            }
            if(chkThurs.isSelected()){
                LabScheduler.recurrence +=  "Thurs, ";
            }
            if(chkFri.isSelected()){
                LabScheduler.recurrence +=  "Fri, ";
            }
            if(chkSat.isSelected()){
                LabScheduler.recurrence +=  "Sat, ";
            }
            
            LabScheduler.recurrence += "effective " + beginDate + " until " + endDate +
                    " from " + startTime + " to " + endTime + ".";
        }
        catch(Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                Optional<ButtonType> result = alert.showAndWait();
            //errorMessage = "";
            return(false);
        }
        return(true);
    }
    
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onBtnOk(ActionEvent event) {
        if(validateAndSet()){
            LabScheduler.fieldsdisabled = true;
            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void onBtnCancel(ActionEvent event) {
        LabScheduler.fieldsdisabled = false;
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();   
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
}
