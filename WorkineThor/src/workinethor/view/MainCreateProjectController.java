/**
 * Controller of the Create project first view.
 */
package workinethor.view;

import java.io.IOException;

import bean.ProjectBean;
import controller.CreateProjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import workinethor.Main;

public class MainCreateProjectController {

	ObservableList<String> driveSelectorList = FXCollections.observableArrayList("Google Drive", "Mega", "DropBox");

	//createProject bean and controller
	private ProjectBean bean = new ProjectBean();
	private CreateProjectController projectController = CreateProjectController.getInstace();
	
	// project information
	@FXML
	private TextField projectNameField;

	@FXML
	private ChoiceBox<String> driveSelector;

	@FXML
	private CheckBox driveBox;

	@FXML
	private Button next;

	// changed for code smells
	@FXML
	private void driveBoxYes() {
		boolean checked = false;

		checked = !driveBox.isSelected();
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
		driveSelector.setValue("");
		
	}

	@FXML
	private void goNext() throws IOException, InterruptedException {
		BorderPane mainLayout = null;
		mainLayout = Main.getMainLayout();
		
		//pass createProject values to the bean class
		bean.setProjectName(projectNameField.getText());
		if(driveBox.isSelected() && driveSelector.getValue() != "") {
			bean.setDriveIsActive(true);
			bean.setDriveName(driveSelector.getValue());
		}
		
		//pass bean to controller so that i can instantiate a new project(model)
		projectController.createProject(bean);

		BorderPane mainLayoutNext = null;
		try {
			mainLayoutNext = FXMLLoader.load(MainNavBarController.class.getResource("CreateProjectNext.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutNext);
	}

	// method that create the add member view
	@FXML
	private void addMember() {
		ObservableList<String> memberListSelector = FXCollections.observableArrayList("culo", "anatra"); //lista di "membri"
		Stage addMemberWindow = new Stage();
		addMemberWindow.setTitle("Add Member");

		AnchorPane background = new AnchorPane();

		TextField searchField = new TextField(); //creazione barra di ricerca
		searchField.setPromptText("Search here!");
		searchField.setTranslateX(101);
		searchField.setTranslateY(52);
		searchField.setPrefSize(250, 26);

		Image searchLogo = new Image("Images/search--v2.png", 36, 36, true, false);
		ImageView logoView = new ImageView(searchLogo);
		logoView.setTranslateX(50);
		logoView.setTranslateY(47);

		ListView<String> memberList = new ListView<>(memberListSelector); //visualizzazione membri in una list view
		memberList.setTranslateY(96);
		memberList.setPrefSize(400, 500);
		memberList.setItems(memberListSelector);
		memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		searchField.setOnKeyTyped(event -> { //algoritmo di sorting per cercare i membri nella lista in base ai caratteri 
			String search = searchField.getText().toString();
			if (search != null && search.equals(memberListSelector)) {
			}
		});

		background.getChildren().add(logoView);
		background.getChildren().add(searchField);
		background.getChildren().add(memberList);

		Scene loginScene = new Scene(background, 400, 600);
		addMemberWindow.setScene(loginScene);
		addMemberWindow.setResizable(false);
		addMemberWindow.initModality(Modality.APPLICATION_MODAL);

		addMemberWindow.show();
	}
	
}