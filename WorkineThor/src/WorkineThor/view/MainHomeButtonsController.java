package WorkineThor.view;

import java.io.IOException;

import WorkineThor.Main;
import javafx.fxml.FXML;

public class MainHomeButtonsController {
	private Main main;

	@FXML
	private void goCreate() throws IOException {
		main.showMainItems();
	}

}
