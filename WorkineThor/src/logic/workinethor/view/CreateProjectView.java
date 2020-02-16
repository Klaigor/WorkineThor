/**
 * Graphic controller of the Create project first view.
 */
package logic.workinethor.view;

import java.io.IOException;
import java.sql.SQLException;

import logic.bean.ProjectBean;
import logic.controller.CreateProjectController;
import logic.database.UserDAO;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import logic.workinethor.Main;

public class CreateProjectView {

	ObservableList<String> driveSelectorList = FXCollections.observableArrayList("Google Drive", "Mega", "DropBox");

	// createProject bean and controller 
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

	//changed for test
	// changed for code smells
	@FXML
	private boolean driveBoxYes() {
		boolean checked = false;

		checked = !driveBox.isSelected();
		driveSelector.setDisable(checked);
		return true;
	}

	//changed for test
	@FXML
	private boolean nextYes() {
		String textFieldValue = projectNameField.getText();
		boolean isDisabled = (textFieldValue.isEmpty() || textFieldValue.trim().isEmpty());
		next.setDisable(isDisabled);
		return true;
	}

	//changed for test
	@FXML
	private boolean initialize() {
		next.setDisable(true);
		driveSelector.setDisable(true);
		driveSelector.setItems(driveSelectorList);
		driveSelector.setValue("");
		return true;
	}

	//changed for test
	@FXML
	private boolean goNext() throws IOException, InterruptedException {
		BorderPane mainLayout = null;
		mainLayout = Main.getMainLayout();

		// pass createProject values to the bean class
		bean.setProjectName(projectNameField.getText());
		if (driveBox.isSelected() && driveSelector.getValue() != "") {
			bean.setDriveIsActive(true);
			bean.setDriveName(driveSelector.getValue());
		}

		// pass bean to controller so that i can instantiate a new project(model)
		projectController.createProject(bean);

		BorderPane mainLayoutNext = null;
		try {
			mainLayoutNext = FXMLLoader.load(NavBarView.class.getResource("CPAddFile.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutNext);
		return true;
	}

	//changed for test
	// method that create the add member view
	@FXML
	private boolean addMember() throws SQLException {
		UserDAO usrDAO = new UserDAO();
		ObservableList<String> result = usrDAO.getAllUsers();
		ObservableList<String> memberListSelector = FXCollections.observableArrayList(); // Create a member list
		Stage addMemberWindow = new Stage();
		addMemberWindow.setTitle("Add Member");

		AnchorPane background = new AnchorPane();

		TextField searchField = new TextField(); // create a search field
		searchField.setPromptText("Search here!");
		searchField.setTranslateX(101);
		searchField.setTranslateY(52);
		searchField.setPrefSize(250, 26);

		Image searchLogo = new Image("logic/Images/search--v2.png", 36, 36, true, false);
		ImageView logoView = new ImageView(searchLogo);
		logoView.setTranslateX(50);
		logoView.setTranslateY(47);

		ListView<String> memberList = new ListView<>(memberListSelector); // Create a list view where I can visualize
																			// the list
		memberList.setTranslateY(96);
		memberList.setPrefSize(400, 450);
		memberList.setItems(memberListSelector);
		memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		memberList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldVal, String newVal) -> {
			String memberSelected = memberList.getSelectionModel().getSelectedItem(); 
			System.out.println(memberSelected);
			//!!!!!!!!!!!!!!!!!aggiungi membro al progetto!!!!!!!!!!!!!!!!!!!!
			bean.setUserToAdd(memberSelected);
			projectController.createProject(bean);
		});
		
		memberListSelector.addAll(result);
		
		Button addButton = new Button();
		addButton.setText("Add");
		addButton.setPrefSize(70, 40);
		addButton.setTranslateY(553);
		addButton.setTranslateX(170);

		FilteredList<String> filteredData = new FilteredList<>(memberListSelector, s -> true); // create a filtered
																								// member list
		searchField.textProperty().addListener(obs -> { // Compare if in the list there are some equals with the
														// filtered list
			String filter = searchField.getText();
			if (filter == null || filter.length() == 0) {
				filteredData.setPredicate(s -> true);
			} else {
				filteredData.setPredicate(s -> s.contains(filter));
			}
			memberList.setItems(filteredData); // show filtered list
		});

		background.getChildren().add(logoView);
		background.getChildren().add(searchField);
		background.getChildren().add(memberList);
		background.getChildren().add(addButton);

		Scene loginScene = new Scene(background, 400, 600);
		addMemberWindow.setScene(loginScene);
		addMemberWindow.setResizable(false);
		addMemberWindow.initModality(Modality.APPLICATION_MODAL);
		
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//implementare che aggiunge il membro selezionato
			}
			
		});

		addMemberWindow.show();
		
		return true;
	}

}
