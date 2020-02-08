/**
 * Controller of the Create project first view.
 */
package workineThor.view;

import java.io.IOException;

import workineThor.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainCreateProjectController {
	private Main main = Main.getIstance();
	private BorderPane mainLayout = null;

	ObservableList<String> DriveSelectorList = FXCollections.observableArrayList("Google Drive", "Mega", "DropBox");

	// project information
	@FXML
	private TextField ProjectNameField;

	@FXML
	private ChoiceBox<String> DriveSelector;

	@FXML
	private CheckBox DriveBox;

	@FXML
	private Button Next;

	private static String projectName;

	//private static Boolean Drive = true;
	
	@FXML
	private void DriveBoxYes() {
		if (DriveBox.isSelected()) {
			DriveSelector.setDisable(false);
		//	Drive = false;
			
		} else {
			DriveSelector.setDisable(true);
		//	Drive = true;
		}
	}

	@FXML
	private void nextYes() {
		projectName = ProjectNameField.getText();
		boolean isDisabled = (projectName.isEmpty() || projectName.trim().isEmpty());
		Next.setDisable(isDisabled);
	}

	@FXML
	private void initialize() {
		Next.setDisable(true);
		DriveSelector.setDisable(true);
		DriveSelector.setItems(DriveSelectorList);
		DriveSelector.setValue("mmm");
		
	}

	@FXML
	private void goNext() throws IOException {
		try {
			mainLayout = FXMLLoader.load(MainBackHomeController.class.getResource("CreateProjectNext.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		main.setLayoutCenter(mainLayout);
	}

	public static String getProjectName() {
		return projectName;
	}

	//public static Boolean getDrive() {
	//	return Drive;

	}
//}