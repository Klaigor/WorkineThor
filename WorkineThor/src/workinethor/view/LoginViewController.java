package workinethor.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import workinethor.Main;

public class LoginViewController {

	private BorderPane mainLayout = Main.getMainLayout();
	private BorderPane mainLayoutHome = null;

	String user = "JavaFX2";
	String pw = "password";
	String checkUser;
	String checkPw;

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginBtn;

	@FXML
	public void initialize() {

		// Action for loginBtn
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) { // "event" is not used
				checkUser = userName.getText().toString();
				checkPw = password.getText().toString();
				if (checkUser.equals(user) && checkPw.equals(pw)) {
					try {
						mainLayoutHome = FXMLLoader.load(MainNavBarController.class.getResource("HomePage.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					mainLayout.setCenter(mainLayoutHome);
				}
				userName.setText("");
				password.setText("");
			}
		});

	}

}
