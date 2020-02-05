package workineThor;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	//singleton instance
	private static Main instance = null;
	
	private Stage primaryStage;
	private BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException { 
		
		//active Main instance(thread)
		instance = this;
		
		//setup primaryStage
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("WorkineThor");
		
		showMainView();
		
		//add scene mainLayout to window
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		showHomeScene();
		
		System.out.println(this);
	}
	
	//load navigation bar
	public void showMainView() throws IOException {
		mainLayout = FXMLLoader.load(Main.class.getResource("view/MainView.fxml"));
	}

	//load create project scene
	public void showMainItems() throws IOException {
		mainLayout.setCenter(FXMLLoader.load(Main.class.getResource("view/MainCreateProjectItems.fxml")));
	}
	
	//load home page scene
	public void showHomeScene() throws IOException {
		mainLayout.setCenter(FXMLLoader.load(Main.class.getResource("view/HomePage.fxml")));
	}

	//load create project next step
	public void showCreateProjectNext() throws IOException {
		mainLayout.setCenter(FXMLLoader.load(Main.class.getResource("view/CreateProjectNext.fxml")));
	}

	//get singleton instance
	public static Main getIstance() {
		if(instance == null)
			instance = new Main();
		return instance;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}