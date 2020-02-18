/**
 * Home controler, Home should be the main page shown sfter login, 
 * containing the various bounderies for the possible use cases
 */
package logic.workinethor.view;

import logic.bean.ProjectBean;
import logic.database.ProjectDAO;
import logic.model.Session;
import logic.workinethor.Main;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.event.EventHandler;



public class HomePageView {
	
	private BorderPane mainLayout = Main.getMainLayout();
	private BorderPane progDutiesLayout = null;
	
	@FXML
	private BorderPane background;
	
	private Session activeSession = Session.getSession();
	
	@FXML
	private void initialize() {
		
		ProjectDAO projectDAO = new ProjectDAO();
		AnchorPane items = new AnchorPane();
			
		Popup popup = new Popup();
		popup.setAutoHide(true);
		ListView<String> popupListView = new ListView<>();
		popupListView.getItems().add("Duty1");
		popupListView.getItems().add("Duty1");
		popupListView.getItems().add("Duty1");
		popupListView.getItems().add("Duty1");
		popupListView.getItems().add("Duty1");
		
		popup.getContent().add(popupListView);
		
		popupListView.setStyle("-fx-background-color:yellow");
		
		Label loggedUserLabel = new Label();
		loggedUserLabel.setText("Username: " + activeSession.getLoggedUser().getUsername());
		loggedUserLabel.setFont(new Font("Arial", 24));
		loggedUserLabel.setTranslateY(5);
		loggedUserLabel.setStyle("-fx-text-fill: #cfd1dd");
		
		Label projectLabel = new Label();
		projectLabel.setText("Your Projects:");
		projectLabel.setFont(new Font("Arial", 24));
		projectLabel.setTranslateY(50);
		projectLabel.setStyle("-fx-text-fill: #cfd1dd");
		
		ObservableList<String> userProjects = projectDAO.getAllUserProjects(Session.getSession().getLoggedUser());
		ListView<String> allProjectsListView = new ListView<String>(userProjects);
		allProjectsListView.setTranslateY(100);
		allProjectsListView.setTranslateX(1);
		allProjectsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				
				ListCell<String> cell = new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setFont(new Font("Arial", 18));
                        }
					}
				};
				cell.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if(event.isPrimaryButtonDown() && !cell.isEmpty()) {
							//popup.show(Main.getMainWindow());
							
							ProjectBean bean = new ProjectBean();
							bean.setProjectName(cell.getText());
							Session.getSession().setCurrentBrowsingProject(bean);

							BorderPane projectBorderPane = null;
							try {
								projectBorderPane = FXMLLoader.load(HomePageView.class.getResource("Project.fxml"));
							} catch (IOException e) {}
							
							mainLayout.setCenter(projectBorderPane);
							
						}
					}
				});
				
				return cell;
			}
		});
		
		allProjectsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue != newValue) {
					System.out.println(newValue);
				}
			}
		});
		
		items.getChildren().add(loggedUserLabel);
		items.getChildren().add(projectLabel);
		items.getChildren().add(allProjectsListView);
		
		//System.out.println(userProjects);
		background.setCenter(items);
		background.setStyle("-fx-background-color: #2d3447");
	}
}
