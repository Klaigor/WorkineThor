package workineThor;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//import mega.MegaHandler;

public class Main extends Application {
	//singleton instance
	private static Main instance = null;
	
	private BorderPane mainLayout;
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws IOException { 
		
		//active Main instance(thread)
		instance = this;
		
		//setup primaryStage
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("WorkineThor");
		
		//load main view
		mainLayout = FXMLLoader.load(Main.class.getResource("view/MainView.fxml"));
		
		//add scene mainLayout to window
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//load home screen(empty page)
		mainLayout.setCenter(FXMLLoader.load(Main.class.getResource("view/HomePage.fxml")));
	}

	//get singleton instance
	public static Main getIstance() {
		if(instance == null)
			instance = new Main();
		return instance;
	}
	
	public void setMainLayout(BorderPane layout) {
		mainLayout.setCenter(layout);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}