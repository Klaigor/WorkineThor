/**
 * Graphic controller for the Browse project page
 */
package logic.workinethor.view;


import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import logic.database.ProjectDAO;

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
