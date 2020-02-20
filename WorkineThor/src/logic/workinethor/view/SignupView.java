/**
 * graphic controller of the SignupView.fxml handles the graphic part of the 
 * signup use case
 */
package logic.workinethor.view;

import logic.workinethor.Main;
import java.io.IOException;
import java.sql.SQLException;
import logic.bean.UserBean;
import logic.controller.LoginController;
import logic.exceptions.UserAlreadyExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;	

public class SignupView {
	
	private BorderPane mainLayout = Main.getMainLayout();
	private BorderPane homeLayout = null;
	private String textFillStyle = "-fx-text-fill: #cfd1dd";
	private String backgroundStyle = "-fx-background-radius: 10";
	@FXML
	private TextField username;

	@FXML
	private TextField password;
	
	@FXML 
	private Button signupBtn;
	
	@FXML
	private BorderPane pane;
	
	@FXML
	private Label uname;
	
	@FXML
	private Label pass;
	
	@FXML
	private Label title;

	@FXML
	public void initialize() {
		
		title.setText("WorkineThor");
		title.setTranslateX(-68);
		title.setTranslateY(82);
		title.setFont(new Font("Arial", 70));
		title.setStyle(textFillStyle);
		
		username.setStyle(backgroundStyle);
		uname.setStyle(textFillStyle);
		password.setStyle(backgroundStyle);
		pass.setStyle(textFillStyle);
		signupBtn.setUnderline(true);
		signupBtn.setStyle(backgroundStyle);
		
		pane.setStyle("-fx-background-color: #2d3447");
		
		signupBtn.setOnAction(new EventHandler<ActionEvent>() {

			/*
			 * gets username and password from the view, puts them in a bean 
			 * and sends them to the controller which will handle the rest of the UseCase
			 * redirects to the Homepage
			 */
			@Override
			public void handle(ActionEvent event) { // "event" is not used
				UserBean user = new UserBean();
				user.setPassword(password.getText().toString());
				user.setUsername(username.getText().toString());
				
				LoginController control = new LoginController();  
				try {
					
					control.signup(user);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (UserAlreadyExistException e) {
					e.printStackTrace();
				}

								
				try {
						homeLayout = FXMLLoader.load(NavBarView.class.getResource("HomePage.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					mainLayout.setCenter(homeLayout);
					NavBarView.getToolbar().setVisible(true);
			}	
		});
	}
}
