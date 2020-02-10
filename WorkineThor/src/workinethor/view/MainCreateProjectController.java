/**
 * Controller of the Create project first view.
 */
package workinethor.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import mega.MegaHandler;

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
	private Logger logger = Logger.getLogger(CreateProjectNextController.class.getName());

	//changed for code smells
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
		driveSelector.setValue("mmm");
		
		//changeListener -> allows us to capture event on choicebox
		ChangeListener<String> myListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String value, String newValue) {
				if(newValue == "Mega")
					megaLogin();
			}
		};
		
		//added listener to choiceBox
		driveSelector.getSelectionModel().selectedItemProperty().addListener(myListener);
	}

	//method to create the megaLoginPage
	private void megaLogin() {
		Stage loginWindow = new Stage();
		loginWindow.setTitle("Mega Login");
		
		AnchorPane background = new AnchorPane();
		
		Label email = new Label("Email");
		Label password = new Label("Password");
		email.setTranslateX(50); email.setTranslateY(120);
		password.setTranslateX(50); password.setTranslateY(200);
		
		TextField emailTextField = new TextField();
		TextField passwordTextField = new TextField();
		emailTextField.setTranslateX(150); emailTextField.setTranslateY(120);
		passwordTextField.setTranslateX(150); passwordTextField.setTranslateY(200);
		
		Button loginButton = new Button();
		loginButton.setTranslateX(150); loginButton.setTranslateY(280);
		loginButton.setText("Login");
		
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				//check if email and pass are correct
				int ret = 0;
				MegaHandler mh = new MegaHandler(emailTextField.getText(), passwordTextField.getText());
				try {
					ret = mh.login();
				}catch(IOException e) {}
				
				logger.log(Level.INFO, mh.get_user());
				
				if(ret == 0)
					logger.log(Level.WARNING, "invalid email or password");
				loginWindow.close();
			}
		});
		
		Image megaLogo = new Image("Images/mega.png", 220, 90, true, false);
		ImageView logoView = new ImageView(megaLogo);
		logoView.setTranslateX(80); logoView.setTranslateY(10);
		
		background.getChildren().add(email);
		background.getChildren().add(password);
		background.getChildren().add(emailTextField);
		background.getChildren().add(passwordTextField);
		background.getChildren().add(loginButton);
		background.getChildren().add(logoView);
		
		Scene loginScene = new Scene(background,450,450);
		loginWindow.setScene(loginScene);
		loginWindow.setResizable(false);
		loginWindow.initModality(Modality.APPLICATION_MODAL);
		
		loginWindow.show();
	}
	
	@FXML
	private void goNext() throws IOException, InterruptedException {
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