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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Callback;
import logic.bean.ProjectBean;
import logic.controller.CreateProjectController;
import logic.database.ProjectDAO;
import logic.model.Session;
import logic.workinethor.Main;

public class BrowseProjectView {

	@FXML
	private BorderPane background;

	@FXML
	private void initialize() throws SQLException {
		ProjectDAO projectDAO = new ProjectDAO();
		AnchorPane items;

		ObservableList<String> result = projectDAO.getAllProjects();
		ObservableList<String> projectListSelector = FXCollections.observableArrayList();

		items = new AnchorPane();

		TextField search = new TextField();
		search.setPromptText("Search here!");
		search.setTranslateX(300);
		search.setTranslateY(52);
		search.setPrefSize(250, 26);
		search.setStyle("-fx-background-radius: 10");

		Image searchImage = new Image("logic/Images/search--v2.png", 36, 36, true, false);
		ImageView logo = new ImageView(searchImage);
		logo.setTranslateX(249);
		logo.setTranslateY(47);

		Circle circle = new Circle(20, 20, 20);
		circle.setTranslateX(247);
		circle.setTranslateY(46);
		circle.setFill(javafx.scene.paint.Color.AZURE);

		ListView<String> projectList = new ListView<>(projectListSelector);
		projectList.setTranslateY(96);
		projectList.setTranslateX(205);
		projectList.setPrefSize(400, 500);
		projectList.setItems(projectListSelector);
		projectList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		projectList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				ListCell<String> project = new ListCell<String>() {
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

							Session activeSession = Session.getSession();

							activeSession.setCurrentBrowsingProject(bean);

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
		search.textProperty().addListener(obs -> { // Compare if in the list there are some equals with the
													// filtered list
			String filter = search.getText();
			if (filter == null || filter.length() == 0) {
				filteredData.setPredicate(s -> true);
			} else {
				filteredData.setPredicate(s -> s.contains(filter));
			}
			projectList.setItems(filteredData); // show filtered list
		});

		items.getChildren().addAll(circle, logo);
		items.getChildren().add(search);
		items.getChildren().add(projectList);

		background.setCenter(items);
		background.setStyle("-fx-background-color: #2d3447");

	}
}
