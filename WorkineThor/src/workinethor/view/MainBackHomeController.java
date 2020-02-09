package workinethor.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import workinethor.Main;

public class MainBackHomeController {
	private BorderPane mainLayout = null;
//	private BorderPane mainLayoutHome = null;
//	private BorderPane mainLayoutItems = null;
//	private BorderPane mainLayoutLogin = null;
	
	@FXML
	private void goHome(Node mainLayoutHome) throws IOException {
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutHome = FXMLLoader.load(MainBackHomeController.class.getResource("HomePage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutHome);
	}
	
	@FXML
	private void goCreate(Node mainLayoutItems) throws IOException {
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutItems = FXMLLoader.load(Main.class.getResource("view/MainCreateProjectItems.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutItems);
		
	}
	@FXML
	private void goLogin(Node mainLayoutLogin) throws IOException {
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutLogin = FXMLLoader.load(Main.class.getResource("view/LoginView.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutLogin);
	}

}