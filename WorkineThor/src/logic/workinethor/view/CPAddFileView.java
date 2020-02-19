package logic.workinethor.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.FileBean;
import logic.controller.AddFileController;
import logic.exceptions.FileAlreadyExistsException;
import logic.model.Session;
import logic.workinethor.Main;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CPAddFileView {

	// get singleton instance
	private AddFileController controller = AddFileController.getInstace();

	// addFile bean
	private FileBean fileBean = new FileBean();

	private Logger logger = Logger.getLogger(CPAddFileView.class.getName());
	
	//FXML values
	@FXML
	private AnchorPane anchor;
	
	@FXML
	private Button addfile;
	
	@FXML
	private ListView<String> filelist = new ListView<>();
	
	@FXML
	private Label title;
	
	@FXML
	private Button addFileDrive;
	
	@FXML
	private BorderPane pane;

	private static final String SFONDO = "-fx-text-fill: #cfd1dd";
	private static final String RADIUS = "-fx-background-RADIUS: 10";
	
	//changed for test
	@FXML
	private boolean initialize() {
	
		title.setText(Session.getSession().getCurrentBrowsingProject().getProjectName());
		title.setFont(new Font("Arial", 70));
		title.setStyle(SFONDO); 
		
		Button doneButton = new Button();
		doneButton.setText("Done");
		doneButton.setTranslateX(350);
		doneButton.setTranslateY(220);
		doneButton.setStyle(RADIUS);
		doneButton.setUnderline(true);
		
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BorderPane projectPane = null;
				try {
					projectPane = FXMLLoader.load(CPAddFileView.class.getResource("Project.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Main.getMainLayout().setCenter(projectPane);
			}
		});
		
		anchor.getChildren().add(doneButton);
		
		addFileDrive.setUnderline(true);
		addFileDrive.setStyle(RADIUS);
		addfile.setUnderline(true);
		addfile.setStyle(RADIUS);
		addfile.setUnderline(true);

		pane.setStyle("-fx-background-color: #2d3447");

		
		addFileDrive.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addFileDriveFunc();
			}
		});

		// disable button if driveActive is false
		if (!Session.getSession().getCurrentBrowsingProject().getDriveActive())
			addFileDrive.setDisable(true);
		return true; 
	}

	//changed for test
	// add files to project function(needs to handled by the addFileController)
	@FXML
	private boolean addFileFunc() {
		boolean success = false;
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);

		if (selectedFile != null) {
			filelist.getItems().add(selectedFile.getPath());
			fileBean.setFilePath(selectedFile.getPath());
			fileBean.setFileName(selectedFile.getName());
			try {
				controller.addFileToProject(fileBean, Session.getSession().getCurrentBrowsingProject().getProjectName());
			} catch (FileAlreadyExistsException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("File already exists in project");
				alert.show();
			}
			success = true;
		} else {
			logger.log(Level.WARNING, "No file selected");
			success = false;
		}
		return success;
	}

	//changed for test
	// add drive file to project function(needs to be handled by addFileController)
	@FXML
	private boolean addFileDriveFunc() {
		WebView megaWebPage = new WebView();
		WebEngine megaEngine = megaWebPage.getEngine();
		
		Stage megaPage = new Stage();
		megaPage.setTitle("megaFiles");
		megaPage.initModality(Modality.APPLICATION_MODAL);
		megaPage.setResizable(false);

		AnchorPane background = new AnchorPane();
		Label fileNameLabel = new Label("Insert URL");
		fileNameLabel.setTranslateX(20);
		fileNameLabel.setTranslateY(50);
		fileNameLabel.setStyle(SFONDO);

		TextField fileURL = new TextField();
		fileURL.setTranslateX(100);
		fileURL.setTranslateY(50);
		fileURL.setStyle(RADIUS);
		
		Label nameLabel = new Label("File Name");
		nameLabel.setTranslateX(300);
		nameLabel.setTranslateY(50);
		nameLabel.setStyle(SFONDO);
		
		TextField fileNameTextField = new TextField();
		fileNameTextField.setTranslateX(380);
		fileNameTextField.setTranslateY(50);
		fileNameTextField.setStyle(RADIUS);

		Button loadFileButton = new Button();
		loadFileButton.setText("Load");
		loadFileButton.setTranslateX(60);
		loadFileButton.setTranslateY(100);
		loadFileButton.setStyle(RADIUS);
		loadFileButton.setUnderline(true);

		loadFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (fileURL.getText().contains("https:") && fileURL.getText().length() < 50 && !fileNameTextField.getText().isEmpty()) {
					filelist.getItems().add(fileURL.getText());
					fileBean.setFilePath(fileURL.getText());
					fileBean.setFileName(fileNameTextField.getText());
					try {
						controller.addFileToProject(fileBean, Session.getSession().getCurrentBrowsingProject().getProjectName());
					} catch (FileAlreadyExistsException e) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setContentText("File already exists in project");
						alert.show();
					}
				}
				else logger.log(Level.INFO, "Empty name field");
			}
		});

		Button exitButton = new Button();
		exitButton.setText("Done");
		exitButton.setTranslateX(120);
		exitButton.setTranslateY(100);
		exitButton.setStyle(RADIUS);
		exitButton.setUnderline(true);

		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				megaPage.close();
			}
		});

		switch (Session.getSession().getCurrentBrowsingProject().getDriveName()) {
		case "Google Drive":
			megaEngine.load("https://drive.google.com/");
			break;
		case "Dropbox":
			megaEngine.load("https://dropbox.com/");
			break;
		default:
			megaEngine.load("https://mega.nz");
			break;
		}

		megaEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
				if (newState == State.SUCCEEDED) {
					logger.log(Level.INFO, "Page loaded");
				}
			}
		});

		megaWebPage.setTranslateY(150);
		megaWebPage.setPrefSize(1024, 800);

		background.getChildren().add(fileNameLabel);
		background.getChildren().add(fileURL);
		background.getChildren().add(nameLabel);
		background.getChildren().add(fileNameTextField);
		background.getChildren().add(loadFileButton);
		background.getChildren().add(exitButton);
		background.getChildren().add(megaWebPage);
		
		background.setStyle("-fx-background-color: #2d3447");
		
		Scene megaScene = new Scene(background, 1024, 800);
		megaPage.setScene(megaScene);
		megaPage.show();
		
		return true;
	}

}
