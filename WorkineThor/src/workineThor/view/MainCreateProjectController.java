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

public class MainCreateProjectController {
	private Main main = Main.getIstance();

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

	@FXML
	private void DriveBoxYes() {
		if (DriveBox.isSelected()) {
			DriveSelector.setDisable(false);
			;
		} else {
			DriveSelector.setDisable(true);
		}
	}

	@FXML
	private void nextYes() {
		String projectName = ProjectNameField.getText();
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
		main.showCreateProjectNext();
	}

}