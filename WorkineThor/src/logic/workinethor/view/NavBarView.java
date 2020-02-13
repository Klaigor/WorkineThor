package logic.workinethor.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import logic.workinethor.Main;

public class NavBarView {
	private BorderPane mainLayout = null;

	@FXML
	private void goHome() throws IOException {
		BorderPane mainLayoutHome = null;
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutHome = FXMLLoader.load(NavBarView.class.getResource("HomePage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutHome);
	}

	@FXML
	private void goCreate() throws IOException {
		BorderPane mainLayoutItems = null;
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutItems = FXMLLoader.load(Main.class.getResource("view/CreateProject.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutItems);
	}

	// logout from account
	@FXML
	private void goLogin() throws IOException {
		BorderPane mainLayoutLogin = null;
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutLogin = FXMLLoader.load(Main.class.getResource("view/Login.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutLogin);
	}

}
