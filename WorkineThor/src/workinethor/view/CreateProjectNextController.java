package workinethor.view;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateProjectNextController {
	
	private String projectName = MainCreateProjectController.getProjectName();
	private boolean loginSuccess = MainCreateProjectController.getLoginSuccess();
	
	// array of file paths
	private ArrayList<String> paths = new ArrayList<>();
	private ArrayList<String> urls = new ArrayList<>();
	
	private Logger logger = Logger.getLogger(CreateProjectNextController.class.getName());

	@FXML
	private Button addfile;
	@FXML
	private ListView<String> filelist = new ListView<>();
	@FXML
	private Label title;
	@FXML
	private Button addFileDrive;

	@FXML
	private void initialize() {
		title.setText(projectName);
		
		addFileDrive.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addFileDriveFunc();
			}
		});
		
		//disables button if megaLogin failed
		if(!loginSuccess)
			addFileDrive.setDisable(true);
	}

	//add files to project function
	@FXML
	private void addFileFunc() {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);

		if (selectedFile != null) {
			filelist.getItems().add(selectedFile.getPath());
			paths.add(selectedFile.getPath());
		} 
		else
			logger.log(Level.WARNING,"No file selected");
	}

	// returns the i-th file path
	public String readPath(int i) {
		if (i > 0 && i < paths.size()) {
			return paths.get(i);
		} 
		else {
			logger.log(Level.SEVERE,"Out of bounds");
			return null;
		}
	}
	
	//add drive file to project function
	@FXML
	private void addFileDriveFunc() {
		try {
			Desktop.getDesktop().browse(new URI("http://mega.nz/fm/nSJlAKrb"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		Stage megaPage = new Stage();
		megaPage.setTitle("megaFiles");
		megaPage.initModality(Modality.APPLICATION_MODAL);
		megaPage.setResizable(false);
		
		AnchorPane background = new AnchorPane();
		Label fileNameLabel = new Label("Insert URL");
		fileNameLabel.setTranslateX(20); fileNameLabel.setTranslateY(50);
		
		TextField fileURL = new TextField();
		fileURL.setTranslateX(100); fileURL.setTranslateY(50);
		
		ListView<String> selectedFiles = new ListView<String>();
		selectedFiles.setTranslateY(200);
		selectedFiles.setOrientation(Orientation.VERTICAL);
        selectedFiles.setPrefSize(450, 200);
		
		Button loadFileButton = new Button();
		loadFileButton.setText("Load");
		loadFileButton.setTranslateX(60); loadFileButton.setTranslateY(100);
		
		loadFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				selectedFiles.getItems().add(fileURL.getText());
				urls.add(fileURL.getText());
			}
		});
		
		background.getChildren().add(fileNameLabel);
		background.getChildren().add(fileURL);
		background.getChildren().add(selectedFiles);
		background.getChildren().add(loadFileButton);
		
		Scene megaScene = new Scene(background, 450, 450);
		megaPage.setScene(megaScene);
		megaPage.show();
	}
	
	// returns the i-th file url
	public String readURL(int i) {
		if (i > 0 && i < urls.size()) {
			return urls.get(i);
		} 
		else {
			logger.log(Level.SEVERE,"Out of bounds");
			return null;
		}
	}	
}
