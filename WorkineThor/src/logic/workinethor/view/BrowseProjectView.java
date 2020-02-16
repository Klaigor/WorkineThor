/**
 * Graphic controller for the Browse project page
 */
package logic.workinethor.view;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import logic.bean.ProjectBean;
import logic.controller.CreateProjectController;
import logic.database.ProjectDAO;
import logic.workinethor.Main;

public class BrowseProjectView {

	@FXML
	private BorderPane background;

	@FXML
	private void initialize() throws SQLException {
		ProjectDAO projectDAO = new ProjectDAO();
		AnchorPane items = new AnchorPane();

		ObservableList<String> result = projectDAO.getAllProjects();
		ObservableList<String> projectListSelector = FXCollections.observableArrayList();

		items = new AnchorPane();

		TextField searchField = new TextField();
		searchField.setPromptText("Search here!");
		searchField.setTranslateX(300);
		searchField.setTranslateY(52);
		searchField.setPrefSize(250, 26);

		Image searchLogo = new Image("logic/Images/search--v2.png", 36, 36, true, false);
		ImageView logoView = new ImageView(searchLogo);
		logoView.setTranslateX(249);
		logoView.setTranslateY(47);

		ListView<String> projectList = new ListView<>(projectListSelector);
		projectList.setTranslateY(96);
		projectList.setTranslateX(205);
		projectList.setPrefSize(400, 500);
		projectList.setItems(projectListSelector);
		projectList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		projectList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				ListCell project = new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(item);
							setFont(new Font("Arial", 18));
						}
					}
				};
				project.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (event.isPrimaryButtonDown() && !project.isEmpty()) {
							BorderPane mainLayout = null;
							mainLayout = Main.getMainLayout();
							ProjectBean bean = new ProjectBean();
							CreateProjectController pC = CreateProjectController.getInstace();
							
							// pass Project values to the Project page
							bean.setProjectName(project.getText());
							pC.existentProject(bean);
							
							BorderPane mainLayoutProject = null;
							try {
								mainLayoutProject = FXMLLoader.load(NavBarView.class.getResource("Project.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							mainLayout.setCenter(mainLayoutProject);
						}
					}
				});
				return project;
			}
		});

		projectListSelector.addAll(result);

		FilteredList<String> filteredData = new FilteredList<>(projectListSelector, s -> true); // create a filtered
																								// list
		searchField.textProperty().addListener(obs -> { // Compare if in the list there are some equals with the
														// filtered list
			String filter = searchField.getText();
			if (filter == null || filter.length() == 0) {
				filteredData.setPredicate(s -> true);
			} else {
				filteredData.setPredicate(s -> s.contains(filter));
			}
			projectList.setItems(filteredData); // show filtered list
		});

		items.getChildren().add(logoView);
		items.getChildren().add(searchField);
		items.getChildren().add(projectList);

		background.setCenter(items);

	}
}
