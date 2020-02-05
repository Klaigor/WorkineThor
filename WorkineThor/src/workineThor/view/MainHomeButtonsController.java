package workineThor.view;

import java.io.IOException;

import workineThor.Main;
import javafx.fxml.FXML;

public class MainHomeButtonsController {
	private Main main = Main.getIstance();

	@FXML
	private void goCreate() throws IOException {
		main.showMainItems();
	}

}
