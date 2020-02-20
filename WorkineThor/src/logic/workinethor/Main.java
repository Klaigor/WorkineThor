package logic.workinethor;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.exceptions.LoadMainLayoutFailException;

public class Main extends Application {

	/**
	 * NavBar that is always over the top of the view
	 */
	private static BorderPane mainLayout;
	private static Stage primaryStage;
	
	private boolean started = false;
	
	@Override
	public void start(Stage stage) throws IOException {
		// setup primaryStage
		setMainLayout(stage);
		primaryStage.setTitle("WorkineThor");

		// loads NavBar view
		try {
			startMainLayout();
		} catch (LoadMainLayoutFailException e) {
			primaryStage.close();
		}
		
		// add scene mainLayout to window
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		// load login screen
		mainLayout.setCenter(FXMLLoader.load(Main.class.getResource("view/Login.fxml")));
		
		started = true;
	}
	
	public static BorderPane getMainLayout() {
		return mainLayout;
	}
	
	public static Stage getMainWindow() {
		return primaryStage;
	}
	
	//changed for test
	public static boolean startMainLayout() throws IOException, LoadMainLayoutFailException {
		boolean result = true;
		mainLayout = FXMLLoader.load(Main.class.getResource("view/NavBar.fxml"));
		if(mainLayout == null)
			throw new LoadMainLayoutFailException("failed to load MainLayout");
		return result;
	}
	
	public static void setMainLayout(Stage newStage) {
		primaryStage = newStage;
	}
	
	public boolean hasStarted() {
		return started;
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
}