package logic.workinethor.view;

import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import logic.bean.DutyWatchDutiesBean;
import logic.model.Duty;
import logic.model.Project;
import logic.model.Session;

public class WatchDutiesView {
	
    @FXML
    private TableView<DutyWatchDutiesBean> dutiesTable;
    @FXML
    private TableColumn<DutyWatchDutiesBean, String> dutyNameColumn;
    @FXML
    private TableColumn<DutyWatchDutiesBean, String> kindOfDutyColumn;

    @FXML
    private Label dutyNameLabel;
    @FXML
    private Label kindOfDutyLabel;
    @FXML
    private Label stateLabel;
  

    // Reference to the project.
    private Project project = Session.getSession().getCurrentProject();
    ArrayList<Duty> duties = project.getDutyData();
    ListProperty<DutyWatchDutiesBean> dutiesBeans;
    
    
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public WatchDutiesView() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	for (Duty duty : duties) {
    		dutiesBeans.add( new DutyWatchDutiesBean(duty));
    	}
    	
        // Add observable list data to the table
        dutiesTable.setItems(dutiesBeans);
    	
        // Initialize the Duties table with the two columns.
        dutyNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        kindOfDutyColumn.setCellValueFactory(cellData -> cellData.getValue().kindOfDutyProperty());
        
        //clear Duty details.
        showDutyDetails(null);
        
        //listener for selection changes and show the person details when changed.
        dutiesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDutyDetails(newValue));
    
    
    }
 
    /**
     * Fills all text fields to show details about a duty.
     * if the specified Duty is null, all text fields are cleared.
     * 
     * @param duty or null
     */
    private void showDutyDetails(DutyWatchDutiesBean duty) {
    	if (duty != null) {
    		//fill the labels with info the duty object.
    		dutyNameLabel.setText(duty.getName());
    		kindOfDutyLabel.setText(duty.getKindOfDuty());
            stateLabel.setText(duty.getState().name());
    	} else {
    		 // duty is null, remove all the text.
    		dutyNameLabel.setText("");
    		kindOfDutyLabel.setText("");
            stateLabel.setText("");
    	}
    }


}
