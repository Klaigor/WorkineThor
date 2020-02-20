package logic.workinethor.view;

import java.util.ArrayList;

import org.omg.CORBA.PRIVATE_MEMBER;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import logic.bean.DutyWatchDutiesBean;
import logic.controller.WatchDutiesController;
import logic.model.Duty;

public class WatchDutiesView {

	private static final String FXBACKGROUNDCOLOR = "-fx-background-color: #2d3447";
	private static final String FXTEXTFILL = "-fx-text-fill: #cfd1dd";
	private static final String FXRADIUS = "-fx-background-radius: 10";
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
	@FXML
	private Label dutyName;
	@FXML
	private Label kindOfDuty;
	@FXML
	private Label state;
	@FXML
	private Label details;
	@FXML
	private BorderPane pane;
	@FXML
	private AnchorPane anchor;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private AnchorPane ancore;
	@FXML
	private Button newButton;
	@FXML
	private Button delete;
	@FXML
	private Button edit;

	// Reference to the project.
	ArrayList<DutyWatchDutiesBean> dutiesBeans = new ArrayList<>();

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		pane.setStyle(FXBACKGROUNDCOLOR);
		anchor.setStyle(FXBACKGROUNDCOLOR);
		anchorPane.setStyle(FXBACKGROUNDCOLOR);
		ancore.setStyle(FXBACKGROUNDCOLOR);
		dutyNameLabel.setStyle(FXTEXTFILL);
		kindOfDutyLabel.setStyle(FXTEXTFILL);
		stateLabel.setStyle(FXTEXTFILL);
		dutyName.setStyle(FXTEXTFILL);
		kindOfDuty.setStyle(FXTEXTFILL);
		state.setStyle(FXTEXTFILL);
		details.setStyle(FXTEXTFILL);
		newButton.setUnderline(true);
		delete.setUnderline(true);
		edit.setUnderline(true);
		newButton.setStyle(FXRADIUS);
		delete.setStyle(FXRADIUS);
		edit.setStyle(FXRADIUS);

		WatchDutiesController controller = new WatchDutiesController();
		ArrayList<Duty> duties;
		duties = (ArrayList<Duty>) controller.getProjectDuties();

		for (Duty duty : duties) {
			dutiesBeans.add(new DutyWatchDutiesBean(duty));
		} 

		ObservableList<DutyWatchDutiesBean> observableDuty = FXCollections.observableArrayList(dutiesBeans);
		SimpleListProperty<DutyWatchDutiesBean> dutiesProperty = new SimpleListProperty<>(observableDuty);
		// Add observable list data to the table
		dutiesTable.setItems(dutiesProperty);
		dutiesTable.setStyle("-fx-background-color: #2d3447");
		dutiesTable.setStyle("-fx-text-fill: #cfd1dd");
		dutiesTable.setStyle("-fx-background-radius: 10");

		// Initialize the Duties table with the two columns.
		dutyNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		kindOfDutyColumn.setCellValueFactory(cellData -> cellData.getValue().kindOfDutyProperty());

		// clear Duty details.
		showDutyDetails(null);

		// listener for selection changes and show the person details when changed.
		dutiesTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDutyDetails(newValue));

	}

	/**
	 * Fills all text fields to show details about a duty. if the specified Duty is
	 * null, all text fields are cleared.
	 * 
	 * @param duty or null
	 */
	private void showDutyDetails(DutyWatchDutiesBean duty) {
		if (duty != null) {
			// fill the labels with info the duty object.
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
