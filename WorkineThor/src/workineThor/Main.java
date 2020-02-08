package workineThor;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//import mega.MegaHandler;

public class Main extends Application {

	// singleton instance of main window
	private static Main instance = null;
	
	/**
	 * NavBar that is always over the top of the view
	 */
	private BorderPane mainLayout;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws IOException {

		// active Main instance
		instance = this;

		// setup primaryStage
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("WorkineThor");

		// loads NavBar view
		mainLayout = FXMLLoader.load(Main.class.getResource("view/NavBar.fxml"));

		// add scene mainLayout to window
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		// load login screen
		mainLayout.setCenter(FXMLLoader.load(Main.class.getResource("view/LoginView.fxml")));
	}

	/**
	 * get singleton instance (dichiarare costruttore come private)
	 * 
	 * @return = istance of the main
	 */
	public static Main getIstance() {
		if (instance == null)
			instance = new Main();
		return instance;
	}

	/**
	 * method that sets next layout to be shown.
	 * 
	 * @param layout = next view to show.
	 */
	public void setLayoutCenter(BorderPane layout) {
		mainLayout.setCenter(layout);
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}