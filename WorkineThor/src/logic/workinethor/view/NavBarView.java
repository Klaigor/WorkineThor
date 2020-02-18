package logic.workinethor.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import logic.workinethor.Main;

public class NavBarView {
	private BorderPane mainLayout = null;

	@FXML
	private ToolBar toolbar;
	private static ToolBar ret;

	// changed for test
	@FXML
	private boolean initialize() {
		toolbar.setVisible(false);
		ret = toolbar;
		toolbar.setStyle("-fx-background-color: #a0a2ab; -fx-background-radius: 3");
		return true;
	}

	public static ToolBar getToolbar() {
		return ret;
	}

	// changed for test
	@FXML
	private boolean goHome() throws IOException {
		BorderPane mainLayoutHome = null;
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutHome = FXMLLoader.load(NavBarView.class.getResource("HomePage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutHome);
		return true;
	}

	// changed for test
	@FXML
	private boolean goCreate() throws IOException {
		BorderPane mainLayoutItems = null;
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutItems = FXMLLoader.load(Main.class.getResource("view/CreateProject.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutItems);
		return true;
	}

	// logout from account
	@FXML
	private boolean goLogin() throws IOException {
		toolbar.setVisible(false);
		BorderPane mainLayoutLogin = null;
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutLogin = FXMLLoader.load(Main.class.getResource("view/Login.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutLogin);
		return true;
	}

	@FXML
	private boolean goProject() throws IOException {
		BorderPane mainLayoutProject = null;
		mainLayout = Main.getMainLayout();
		try {
			mainLayoutProject = FXMLLoader.load(Main.class.getResource("view/BrowseProject.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLayout.setCenter(mainLayoutProject);
		return true;
	}

}
