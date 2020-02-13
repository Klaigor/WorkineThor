/**
 * graphic controller of the SignupView.fxml handles the graphic part of the 
 * signup use case
 */
package workinethor.view;

import workinethor.Main;
import java.io.IOException;
import java.sql.SQLException;
import bean.UserBean;
import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;	

public class SignupView {
	
	private BorderPane mainLayout = Main.getMainLayout();
	private BorderPane homeLayout = null;
	
	@FXML
	private TextField username;

	@FXML
	private TextField password;
	
	@FXML 
	private Button signupBtn;

	@FXML
	public void initialize() {
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

								
				try {
						homeLayout = FXMLLoader.load(NavBarView.class.getResource("HomePage.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					mainLayout.setCenter(homeLayout);
			}	
		});
	}
}
