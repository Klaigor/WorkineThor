package workineThor.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import workineThor.Main;
import java.io.IOException;
import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginViewController {

	private Main main = Main.getIstance(); // Reference The mainApp (which is the main window)

	String user = "JavaFX2";
	String pw = "password";
	String checkUser, checkPw;

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginBtn;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public LoginViewController() {
	}

	@FXML
	public void initialize() {

		// Action for loginBtn
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				checkUser = userName.getText().toString();
				checkPw = password.getText().toString();
				if (checkUser.equals(user) && checkPw.equals(pw)) {
					// lblMessage.setText("Congratulations!");
					System.out.println("mori");
					// lblMessage.setTextFill(Color.GREEN);
				} else {
					// lblMessage.setText("Incorrect user or pw.");
					// lblMessage.setTextFill(Color.RED);
					System.out.println("mori non loggato");

				}
				userName.setText("");
				password.setText("");
			}
		});

	}

}
