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
    	
        // Initialize the person table with the two columns.
        dutyNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        kindOfDutyColumn.setCellValueFactory(cellData -> cellData.getValue().kindOfDutyProperty());
        
        //clear Duty details.
        showDutyDetails(null);
        
        //listener for selection changes and show the person details when changed.
        dutiesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDutyDetails(newValue));
    
    
    }
 
    /**
     * Fills all text fields to show details about a person.
     * if the specified person is null, all text fields are cleared.
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

/**
    /**
     * called when the user clicks on the delete button.
     
    @FXML
    private void handleDeletePerson() {
    	int selectedIndex = dutiesTable.getSelectionModel().getSelectedIndex();
    	if (selectedIndex>=0) {
    		dutiesTable.getItems().remove(selectedIndex);
    	} else {
    		//Nothing selected.
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(mainApp.getPrimaryStage());
    		alert.setTitle("No selection");
    		alert.setHeaderText("No person selected");
    		alert.setContentText("Please select a person in the table.");
    		
    		alert.showAndWait();
    	}
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     
    @FXML
    private void handleNewPerson() {
    	Person tempPerson = new Person();
    	boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
    	if (okClicked) {
    		mainApp.getPersonData().add(tempPerson);
    	}
    }
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = dutiesTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
*/
}
