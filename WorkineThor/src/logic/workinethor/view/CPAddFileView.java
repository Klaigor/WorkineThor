package logic.workinethor.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.FileBean;
import logic.controller.CreateProjectController;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CPAddFileView {

	// get singleton instance
	private CreateProjectController control = CreateProjectController.getInstace();

	// addFile bean
	private FileBean fileBean = new FileBean();

	private Logger logger = Logger.getLogger(CPAddFileView.class.getName());

	@FXML
	private Button addfile;
	@FXML
	private ListView<String> filelist = new ListView<>();
	@FXML
	private Label title;
	@FXML
	private Button addFileDrive;

	//changed for test
	@FXML
	private boolean initialize() {
		title.setText(control.getProjectName());

		addFileDrive.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addFileDriveFunc();
			}
		});

		// disable button if driveActive is false
		if (!control.getDriveActive())
			addFileDrive.setDisable(true);

		// only for info(can be deleted)
		logger.log(Level.INFO, "Project:" + control.getProjectName() + " Drive:" + control.getDriveName()
				+ " DriveActive" + control.getDriveActive());
		
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
			control.addFile(fileBean);
			control.getProject().showFiles();
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
		boolean success = false;
		Stage megaPage = new Stage();
		megaPage.setTitle("megaFiles");
		megaPage.initModality(Modality.APPLICATION_MODAL);
		megaPage.setResizable(false);

		AnchorPane background = new AnchorPane();
		Label fileNameLabel = new Label("Insert URL");
		fileNameLabel.setTranslateX(20);
		fileNameLabel.setTranslateY(50);

		TextField fileURL = new TextField();
		fileURL.setTranslateX(100);
		fileURL.setTranslateY(50);

		Button loadFileButton = new Button();
		loadFileButton.setText("Load");
		loadFileButton.setTranslateX(60);
		loadFileButton.setTranslateY(100);

		loadFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (fileURL.getText().contains("https:")) {
					filelist.getItems().add(fileURL.getText());
					fileBean.setFilePath(fileURL.getText());
					control.addFile(fileBean);
					control.getProject().showFiles();
				}
			}
		});

		Button exitButton = new Button();
		exitButton.setText("Done");
		exitButton.setTranslateX(120);
		exitButton.setTranslateY(100);

		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				megaPage.close();
			}
		});

		WebView megaWebPage = new WebView();
		WebEngine megaEngine = megaWebPage.getEngine();

		switch (control.getDriveName()) {
		case "Mega":
			megaEngine.load("https://mega.nz");
			break;
		case "Google Drive":
			megaEngine.load("https://drive.google.com/");
			break;
		case "default":
			megaEngine.load("https://dropbox.com/");
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
		background.getChildren().add(loadFileButton);
		background.getChildren().add(exitButton);
		background.getChildren().add(megaWebPage);

		Scene megaScene = new Scene(background, 1024, 800);
		megaPage.setScene(megaScene);
		megaPage.show();
		
		return true;
	}

}