package logic.workinethor.view;

import java.sql.SQLException;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.bean.ProjectBean;
import logic.controller.CreateProjectController;
import logic.database.ProjectDAO;

public class ProjectPageView {
	private CreateProjectController control = CreateProjectController.getInstace();
	
	@FXML
	private BorderPane background;

	@FXML
	private void initialize() throws SQLException {
		ProjectDAO projectDAO = new ProjectDAO();
		ProjectBean bean = new ProjectBean();
		bean.setProjectName(control.getProjectName());
				
		ObservableList<String> result = projectDAO.getAllProjectUsers(bean);
		ObservableList<String> memberListSelector = FXCollections.observableArrayList();
		memberListSelector.addAll(result);
		
		AnchorPane items = new AnchorPane();
		
		
		Label title = new Label();
		title.setText(control.getProjectName());
		title.setTranslateX(355);
		title.setFont(new Font("Arial", 25));
		title.setStyle("-fx-text-fill: #cfd1dd");
		
		ListView<String> member = new ListView<>(memberListSelector);
		member.setTranslateY(50);
		member.setTranslateX(1);
		member.setItems(memberListSelector);
		
		ListView<String> duties = new ListView<>();
		duties.setTranslateY(50);
		duties.setTranslateX(550);	
		
		Button addFile = new Button();
		addFile.setText("Add File");
		addFile.setPrefSize(120, 40);
		addFile.setTranslateY(500);
		addFile.setTranslateX(170);
		addFile.setUnderline(true);
		
		Button addMember = new Button();
		addMember.setText("Add member");
		addMember.setTranslateY(500);
		addMember.setPrefSize(120, 40);
		addMember.setUnderline(true);
		
		addMember.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> result = projectDAO.getAllUsersNotInProject(bean);
				ObservableList<String> memberListSelector = FXCollections.observableArrayList(); // Create a member list
				Stage addMemberWindow = new Stage();
				addMemberWindow.setTitle("Add Member");

				AnchorPane background = new AnchorPane();

				TextField searchField = new TextField(); // create a search field
				searchField.setPromptText("Search here!");
				searchField.setTranslateX(101);
				searchField.setTranslateY(52);
				searchField.setPrefSize(250, 26);

				Image searchLogo = new Image("logic/Images/search--v2.png", 36, 36, true, false);
				ImageView logoView = new ImageView(searchLogo);
				logoView.setTranslateX(50);
				logoView.setTranslateY(47);

				ListView<String> memberList = new ListView<>(memberListSelector); // Create a list view where I can visualize
																					// the list
				memberList.setTranslateY(96);
				memberList.setPrefSize(400, 450);
				memberList.setItems(memberListSelector);
				memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				memberList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldVal, String newVal) -> {
					String memberSelected = memberList.getSelectionModel().getSelectedItem(); 
					System.out.println(memberSelected);
				});
				
				memberListSelector.addAll(result);
				
				Button addButton = new Button();
				addButton.setText("Add");
				addButton.setPrefSize(70, 40);
				addButton.setTranslateY(553);
				addButton.setTranslateX(170);

				FilteredList<String> filteredData = new FilteredList<>(memberListSelector, s -> true); // create a filtered
																										// member list
				searchField.textProperty().addListener(obs -> { // Compare if in the list there are some equals with the
																// filtered list
					String filter = searchField.getText();
					if (filter == null || filter.length() == 0) {
						filteredData.setPredicate(s -> true);
					} else {
						filteredData.setPredicate(s -> s.contains(filter));
					}
					memberList.setItems(filteredData); // show filtered list
				});

				background.getChildren().add(logoView);
				background.getChildren().add(searchField);
				background.getChildren().add(memberList);
				background.getChildren().add(addButton);

				Scene loginScene = new Scene(background, 400, 600);
				addMemberWindow.setScene(loginScene);
				addMemberWindow.setResizable(false);
				addMemberWindow.initModality(Modality.APPLICATION_MODAL);
				
				addButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//implementare che aggiunge il membro selezionato
					}
					
				});

				addMemberWindow.show();
				
			}
		});	
		
		items.getChildren().add(title);
		items.getChildren().add(member);
		items.getChildren().add(duties);
		items.getChildren().add(addMember);
		items.getChildren().add(addFile);
		
		background.setCenter(items);
		background.setStyle("-fx-background-color: #2d3447");
	}

}
