package logic.workinethor.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

import logic.bean.UserBean;
import logic.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import logic.workinethor.Main;

public class LoginView {

	private BorderPane mainLayout = Main.getMainLayout();
	private BorderPane homeLayout = null;
	private BorderPane signupLayout = null;
	private String style = "-fx-text-fill: #cfd1dd"; 
	private String backgroundStyle = "-fx-background-radius: 10";
	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginBtn;
	
	@FXML 
	private Button signupBtn;
	
	@FXML
	private Label username;
	
	@FXML
	private Label passwordLabel;
	
	@FXML
	private BorderPane pane;
	
	@FXML
	private Label singup;
	
	@FXML
	private Label title;
	
	//changed for test
	@FXML
	public boolean initialize() {
		Image image = new Image("logic/Images/background.png", 800, 600, true, false);
		BackgroundImage sfondo = new BackgroundImage(image, null, null, null, null);
		
		title.setText("WorkineThor");
		title.setTranslateX(-180);
		title.setTranslateY(10);
		title.setFont(new Font("Arial", 70));
		title.setStyle(style);
		
		username.setStyle(style);
		passwordLabel.setStyle(style);
		singup.setStyle(style);
		usernameTextField.setStyle(backgroundStyle);
		password.setStyle(backgroundStyle);
		loginBtn.isUnderline();
		loginBtn.setStyle(backgroundStyle);
		signupBtn.setStyle(backgroundStyle);
		signupBtn.setStyle("-fx-background-color:  #b51c1c");
		
		pane.setBackground(new Background(sfondo));
		
		/**
		 * sets action for login button
		 * for now is a mock function thata checks pswd and usr vs two local variables
		 * 
		 */
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) { // "event" is not used
				boolean res = false;
				UserBean user = new UserBean();
				user.setPassword(password.getText().toString());
				user.setUsername(usernameTextField.getText().toString());
				
				LoginController control = new LoginController();  
					
					try {
						res = control.signin(user);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
									
				if (res) {						
						try {
							
							homeLayout = FXMLLoader.load(NavBarView.class.getResource("HomePage.fxml"));
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
					mainLayout.setCenter(homeLayout);
					NavBarView.getToolbar().setVisible(true);
					
				} else { // Add the alert window when the password or the username are not correct
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Username or Password is not correct!");
					alert.show();
					
				}
				usernameTextField.setText("");
				password.setText("");
			}
		});
		
		
		/**
		 * sets action for signup button
		 * opens a dialog where you can SignUp 
		 * 
		 */
	
		signupBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) { // "event" is not used
					try {
						signupLayout = FXMLLoader.load(NavBarView.class.getResource("Signup.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					mainLayout.setCenter(signupLayout);
			}	
		});
		return true;
	}
}


















