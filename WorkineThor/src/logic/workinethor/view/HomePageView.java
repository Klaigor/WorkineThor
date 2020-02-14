/**
 * Home controler, Home should be the main page shown sfter login, 
 * containing the various bounderies for the possible use cases
 */
package logic.workinethor.view;

import logic.database.ProjectDAO;
import logic.model.Session;

import java.util.ArrayList;

import javax.swing.event.MenuDragMouseEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class HomePageView {
	
	@FXML
	private BorderPane background;
	
	private Session activeSession = Session.getSession();
	private AnchorPane items;
	private Label loggedUserLabel;
	private Label projectLabel;
	private Popup popup;
	private ArrayList<Label> allDutiesArrayList;
	private ObservableList<String> userProjects;
	private ListView<String> allProjectsListView;
	private ProjectDAO projectDAO;
	
	@FXML
	private void initialize() {
		projectDAO = new ProjectDAO();
		items = new AnchorPane();
		
		allDutiesArrayList = new ArrayList<>();
		
		popup = new Popup();
		
		loggedUserLabel = new Label();
		loggedUserLabel.setText("Username: " + activeSession.getLoggedUser().getUsername());
		loggedUserLabel.setFont(new Font("Arial", 24));
		loggedUserLabel.setTranslateY(5);
		
		projectLabel = new Label();
		projectLabel.setText("Your Projects");
		projectLabel.setFont(new Font("Arial", 24));
		projectLabel.setTranslateY(50);
		
		userProjects = projectDAO.getAllUserProjects();
		allProjectsListView = new ListView<String>(userProjects);
		allProjectsListView.setTranslateY(100);
		allProjectsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setFont(new Font("Arial", 18));
                        }
					}
				};
			}
		});
		
		allProjectsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue != newValue) {
				}
			}
		});
		
		allProjectsListView.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				if(event.isSecondaryButtonDown()) {
					//apri popup
				}
			}
		});
		
		items.getChildren().add(loggedUserLabel);
		items.getChildren().add(projectLabel);
		items.getChildren().add(allProjectsListView);
		
		//System.out.println(userProjects);
		background.setCenter(items);
	}
}
