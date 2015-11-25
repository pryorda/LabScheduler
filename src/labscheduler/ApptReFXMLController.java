/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labscheduler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 *
 * @author dpryor
 */
public class ApptReFXMLController extends Stage implements Initializable{
    
    @FXML private TextField txtStartTime;
    @FXML private ToggleButton togStartMeridien;
    @FXML private ToggleButton togEndMeridien;
    @FXML private TextField txtEndTime;
    @FXML private CheckBox chkSun;
    @FXML private CheckBox chkMon;
    @FXML private CheckBox chkTues;
    @FXML private CheckBox chkWed;
    @FXML private CheckBox chkThurs;
    @FXML private CheckBox chkFri;
    @FXML private CheckBox chkSat;
    @FXML private TextField txtBeginDate;
    @FXML private TextField txtEndDate;
    @FXML private Button btnOk;
    
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onBtnOk(ActionEvent event) {
        LabScheduler.fieldsdisabled = true;
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onBtnCancel(ActionEvent event) {
        LabScheduler.fieldsdisabled = false;
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
        
    }
}
