package workineThor.view;

import java.io.IOException;

import workineThor.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class MainBackHomeController {
	private Main main = Main.getIstance();
	private BorderPane mainLayoutHome = null;
	private BorderPane mainLayoutItems = null;
	private BorderPane mainLayoutLogin = null;
	
	@FXML
	private void goHome() throws IOException {
		try {
			mainLayoutHome = FXMLLoader.load(MainBackHomeController.class.getResource("HomePage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		main.setLayoutCenter(mainLayoutHome);
	}
	
	@FXML
	private void goCreate() throws IOException {
		try {
			mainLayoutItems = FXMLLoader.load(Main.class.getResource("view/MainCreateProjectItems.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		main.setLayoutCenter(mainLayoutItems);
	}
	@FXML
	private void goLogin() throws IOException {
		try {
			mainLayoutLogin = FXMLLoader.load(Main.class.getResource("view/LoginView.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		main.setLayoutCenter(mainLayoutLogin);
	}

}