package workineThor.view;

import java.io.IOException;

import workineThor.Main;
import javafx.fxml.FXML;

public class MainBackHomeController {
	private Main main = Main.getIstance();

	@FXML
	private void goHome() throws IOException {
		main.showHomeScene();
	}
	
	@FXML
	private void goCreate() throws IOException {
		main.showMainItems();
	}

}