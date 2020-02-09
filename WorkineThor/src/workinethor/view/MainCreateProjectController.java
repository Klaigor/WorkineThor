/**
 * Controller of the Create project first view.
 */
package workinethor.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import workinethor.Main;

public class MainCreateProjectController {

	ObservableList<String> driveSelectorList = FXCollections.observableArrayList("Google Drive", "Mega", "DropBox");

	// project information
	@FXML
	private TextField projectNameField;

	@FXML
	private ChoiceBox<String> driveSelector;

	@FXML
	private CheckBox driveBox;

	@FXML
	private Button next;

	private static String projectName;

	private boolean checked = false;

	//changed for code smells
	@FXML
	private void driveBoxYes() {
		checked = driveBox.isSelected();
		driveSelector.setDisable(checked);
	}

	@FXML
	private void nextYes() {
		String textFieldValue = projectNameField.getText();
		boolean isDisabled = (textFieldValue.isEmpty() || textFieldValue.trim().isEmpty());
		next.setDisable(isDisabled);
	}

	@FXML
	private void initialize() {
		next.setDisable(true);
		driveSelector.setDisable(true);
		driveSelector.setItems(driveSelectorList);
		driveSelector.setValue("mmm");
	}

	@FXML
	private void goNext() throws IOException {
		BorderPane mainLayout = null;
		mainLayout = Main.getMainLayout();
		BorderPane mainLayoutNext = null;
		try {
			mainLayoutNext = FXMLLoader.load(MainBackHomeController.class.getResource("CreateProjectNext.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutNext);
	}

	public static String getProjectName() {
		return projectName;
	}
}