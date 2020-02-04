package WorkineThor.view;

import java.io.IOException;

import WorkineThor.Main;
import javafx.fxml.FXML;

public class MainBackHomeController {
	private Main main;

	@FXML
	private void goHome() throws IOException {
		main.showHomeScene();
	}

}