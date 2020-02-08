package workinethor.view;

import java.util.ArrayList;
import java.io.File;

//import workineThor.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

public class CreateProjectNextController {
	
	//private Main main = Main.getIstance();
	private String projectName = MainCreateProjectController.getProjectName();
	//private Boolean drive = MainCreateProjectController.getDrive();

	// array of file paths
	private ArrayList<String> paths = new ArrayList<String>();

	@FXML
	private Button addfile;
	@FXML
	private ListView<String> filelist = new ListView<String>();
	@FXML
	private Label title;
	@FXML
	private Button addFileDrive;

	@FXML
	private void initialize() {
		title.setText(projectName);
		System.out.println("stronzo");
	}

	// add files to project function
	@FXML
	private void addFileFunc() {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);

		if (selectedFile != null) {
			filelist.getItems().add(selectedFile.getPath());
			paths.add(selectedFile.getPath());
		} else
			System.out.println("no file selected");
	}

	// returns the i-th file path
	public String readPath(int i) {
		if (i > 0 && i < paths.size()) {
			return paths.get(i);
		} else {
			System.out.println("out of bounds\n");
			return null;
		}
	}
}
