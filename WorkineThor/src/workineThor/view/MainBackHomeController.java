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

	@FXML
	private void initialize() {
		try {
			mainLayoutHome = FXMLLoader.load(MainBackHomeController.class.getResource("HomePage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			mainLayoutItems = FXMLLoader.load(Main.class.getResource("view/MainCreateProjectItems.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void goHome() throws IOException {
		main.setMainLayout(mainLayoutHome);
	}
	
	@FXML
	private void goCreate() throws IOException {
		main.setMainLayout(mainLayoutItems);
	}

}