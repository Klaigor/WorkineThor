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
import javafx.scene.layout.BorderPane;
import logic.workinethor.Main;

public class LoginView {

	private BorderPane mainLayout = Main.getMainLayout();
	private BorderPane homeLayout = null;
	private BorderPane signupLayout = null;
	

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginBtn;
	
	@FXML 
	private Button signupBtn;
	
	@FXML
	private Label username;
	
	@FXML
	private Label Password;
	
	@FXML
	private BorderPane pane;
	
	@FXML
	private Label singup;
	
	//changed for test
	@FXML
	public boolean initialize() {
		
		username.setStyle("-fx-text-fill: #cfd1dd");
		Password.setStyle("-fx-text-fill: #cfd1dd");
		pane.setStyle("-fx-background-color: #2d3447");
		singup.setStyle("-fx-text-fill: #cfd1dd");
		loginBtn.isUnderline();

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
				user.setUsername(userName.getText().toString());
				
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
				userName.setText("");
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


















