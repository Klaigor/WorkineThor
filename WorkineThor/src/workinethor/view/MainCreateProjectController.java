/**
 * Controller of the Create project first view.
 */
package workinethor.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.ProjectBean;
import controller.CreateProjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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

	//private static String projectName;
	//private static boolean loginSuccess = false;
	private Logger logger = Logger.getLogger(MainCreateProjectController.class.getName());

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

		//changeListener -> allows us to capture event on choiceBox
		ChangeListener<String> myListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String value, String newValue) {
				if (newValue == "Mega")
					megaLogin();
			}
		};

		// added listener to choiceBox
		driveSelector.getSelectionModel().selectedItemProperty().addListener(myListener);
	}

	// method to create the megaLoginPage
	private void megaLogin() {
		Stage loginWindow = new Stage();
		loginWindow.setTitle("Mega Login");

		AnchorPane background = new AnchorPane();

		Label email = new Label("Email");
		Label password = new Label("Password");
		email.setTranslateX(50);
		email.setTranslateY(120);
		password.setTranslateX(50);
		password.setTranslateY(200);

		TextField emailTextField = new TextField();
		PasswordField passwordTextField = new PasswordField();
		emailTextField.setTranslateX(150);
		emailTextField.setTranslateY(120);
		passwordTextField.setTranslateX(150);
		passwordTextField.setTranslateY(200);

		Button loginButton = new Button();
		loginButton.setTranslateX(150);
		loginButton.setTranslateY(280);
		loginButton.setText("Login");
		loginButton.setDefaultButton(true);

		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// check if email and pass are correct
				boolean ret = verifyMegaLogin(emailTextField.getText(), passwordTextField.getText());
				if(!ret) {
					logger.log(Level.WARNING, "Wrong email or password");
				}
				setLoginSuccessVariable(ret);
				loginWindow.close();
			}
		});

		Image megaLogo = new Image("Images/mega.png", 220, 90, true, false);
		ImageView logoView = new ImageView(megaLogo);
		logoView.setTranslateX(80);
		logoView.setTranslateY(10);

		background.getChildren().add(email);
		background.getChildren().add(password);
		background.getChildren().add(emailTextField);
		background.getChildren().add(passwordTextField);
		background.getChildren().add(loginButton);
		background.getChildren().add(logoView);

		Scene loginScene = new Scene(background, 450, 450);
		loginWindow.setScene(loginScene);
		loginWindow.setResizable(false);
		loginWindow.initModality(Modality.APPLICATION_MODAL);

		loginWindow.show();
	}
	
	private static void setLoginSuccessVariable(boolean value) {
		//loginSuccess = value;
	}

	//verify megaLogin method(andrebbe tolta? ma dove la metto??)
	private boolean verifyMegaLogin(String email, String password) {
		boolean success = false;
		String tempEmail = "";
		String tempPassword = "";
		URL loginUrl = getClass().getResource("/File/login.txt");
		try {
			Scanner x  = new Scanner(new File(loginUrl.getPath()));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext() && !success) {
				tempEmail = x.next();
				tempPassword = x.next();
				
				if(tempEmail.trim().equals(email.trim()) && tempPassword.trim().equals(password.trim()))
					success = true;
			}
			x.close();
			
		}catch(FileNotFoundException e) {
			logger.log(Level.WARNING, "file not found");
		}
		
		return success;
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