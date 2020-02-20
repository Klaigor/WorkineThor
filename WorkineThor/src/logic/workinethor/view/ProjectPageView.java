package logic.workinethor.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.bean.ProjectBean;
import logic.database.FileDAO;
import logic.database.ProjectDAO;
import logic.model.Session;
import logic.workinethor.Main;

public class ProjectPageView {
	
	@FXML
	private BorderPane background;
	
	private static final String FXBACKGROUNDRADIUS = "-fx-background-radius: 10";

	@FXML
	private void initialize() throws SQLException {
		ProjectDAO projectDAO = new ProjectDAO();
		ProjectBean bean = new ProjectBean();
		String currentBrowsingProject = Session.getSession().getCurrentBrowsingProject().getProjectName();
		bean.setProjectName(currentBrowsingProject);
				
		ObservableList<String> result = projectDAO.getAllProjectUsers(bean);
		ObservableList<String> memberListSelector = FXCollections.observableArrayList();
		memberListSelector.addAll(result);
		
		AnchorPane items = new AnchorPane();
		
		
		Label title = new Label();
		title.setText(currentBrowsingProject);
		title.setTranslateX(355);
		title.setFont(new Font("Arial", 25));
		title.setStyle("-fx-text-fill: #cfd1dd");
		
		ListView<String> member = new ListView<>(memberListSelector);
		member.setTranslateY(50);
		member.setTranslateX(1);
		member.setItems(memberListSelector);
		
		FileDAO fileDAO = new FileDAO();
		ObservableList<String> fileList = fileDAO.getAllProjectFiles(currentBrowsingProject); 
		ListView<String> files = new ListView<>(fileList);
		files.setTranslateX(560);
		files.setTranslateY(45);
		
		Button addFile = new Button();
		addFile.setText("Add File");
		addFile.setPrefSize(120, 40);
		addFile.setTranslateY(500);
		addFile.setTranslateX(170);
		addFile.setUnderline(true);
		addFile.setStyle(FXBACKGROUNDRADIUS);
		
		addFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				BorderPane addFilePane = null;
				BorderPane mainLayout = Main.getMainLayout();
				try {
					addFilePane = FXMLLoader.load(ProjectPageView.class.getResource("CPAddFile.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				mainLayout.setCenter(addFilePane);
			}
		});
		
		Button addMember = new Button();
		addMember.setText("Add member");
		addMember.setTranslateY(500);
		addMember.setPrefSize(120, 40);
		addMember.setUnderline(true);
		addMember.setStyle(FXBACKGROUNDRADIUS);
		
		addMember.setOnAction(addMemberEventHandler(projectDAO, bean));
			
		
		//if you are not an admin you can't add Member and Files 
		String admin = projectDAO.getProjectAdmin(bean);
		if(!admin.equals(Session.getSession().getLoggedUser().getUsername())) {
			addMember.setVisible(false);
			addFile.setVisible(false);
		}
		
		Button showDutiesButton = new Button();
		showDutiesButton.setText("Show Duties");
		showDutiesButton.setTranslateX(400);
		showDutiesButton.setTranslateY(500);
		showDutiesButton.setVisible(false);
		showDutiesButton.setStyle(FXBACKGROUNDRADIUS);
		showDutiesButton.setUnderline(true);
		
		showDutiesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage dutiesWindow = new Stage();
				dutiesWindow.setTitle("Duties");
				dutiesWindow.initModality(Modality.APPLICATION_MODAL);
				
				BorderPane dutiesPane = null;
				
				try {
					dutiesPane = FXMLLoader.load(ProjectPageView.class.getResource("DutiesOverview.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Scene dutiesScene = new Scene(dutiesPane);
				dutiesWindow.setScene(dutiesScene);
				dutiesWindow.show();
			}
		});
		
		Button joinRequestButton = new Button();
		joinRequestButton.setText("Request to join");
		joinRequestButton.setTranslateY(500);
		joinRequestButton.setPrefSize(120, 40);
		joinRequestButton.setUnderline(true);
		joinRequestButton.setStyle(FXBACKGROUNDRADIUS);
		joinRequestButton.setUnderline(true);
		
		joinRequestButton.setOnAction(joinEventHandler());
		
		ArrayList<String> mArrayList = new ArrayList<>(memberListSelector);
		for(String m: mArrayList) {
			if(Session.getSession().getLoggedUser().getUsername().equals(m)) {
				joinRequestButton.setVisible(false);
				showDutiesButton.setVisible(true);
			}
		}
		
		items.getChildren().add(title);
		items.getChildren().add(member);
		items.getChildren().add(files);
		items.getChildren().add(addMember);
		items.getChildren().add(addFile);
		items.getChildren().add(showDutiesButton);
		items.getChildren().add(joinRequestButton);
		
		background.setCenter(items);
		background.setStyle("-fx-background-color: #2d3447");
	}
	
	public EventHandler<ActionEvent> joinEventHandler(){
		
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage joinRequestWindow = new Stage();
				joinRequestWindow.setTitle("Request Form");
				joinRequestWindow.initModality(Modality.APPLICATION_MODAL);
				joinRequestWindow.setResizable(false);
				
				AnchorPane requestPane = new AnchorPane();
				
				Label nameLabel = new Label();
				nameLabel.setText("Name");
				nameLabel.setTranslateX(20);
				nameLabel.setTranslateY(20);
				
				TextField nameTextField = new TextField();
				nameTextField.setTranslateX(20);
				nameTextField.setTranslateY(60);
				
				Label surnameLabel = new Label();
				surnameLabel.setText("Surname");
				surnameLabel.setTranslateX(20);
				surnameLabel.setTranslateY(100);
				
				TextField surnameTextField = new TextField();
				surnameTextField.setTranslateX(20);
				surnameTextField.setTranslateY(140);
				
				Label meaningLabel = new Label();
				meaningLabel.setText("Talk about why you would like to join");
				meaningLabel.setTranslateX(220);
				meaningLabel.setTranslateY(20);
				
				TextArea meaningArea = new TextArea();
				meaningArea.setTranslateX(220);
				meaningArea.setTranslateY(60);
				meaningArea.prefWidth(100);
				
				Button endForm = new Button();
				endForm.setText("Complete Request");
				endForm.setTranslateX(20);
				endForm.setTranslateY(180);
				
				endForm.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						joinRequestWindow.close();
					}
				});
				
				requestPane.getChildren().add(nameLabel);
				requestPane.getChildren().add(nameTextField);
				requestPane.getChildren().add(surnameLabel);
				requestPane.getChildren().add(surnameTextField);
				requestPane.getChildren().add(meaningLabel);
				requestPane.getChildren().add(meaningArea);
				requestPane.getChildren().add(endForm);
				
				Scene requestScene = new Scene(requestPane, 600, 400);
				joinRequestWindow.setScene(requestScene);
				joinRequestWindow.show();
			}
		};
		
	}
	
	public EventHandler<ActionEvent> addMemberEventHandler(ProjectDAO projectDAO, ProjectBean bean){
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					ObservableList<String> result = null;
					result = projectDAO.getMembersToAdd(bean);
					
					ObservableList<String> memberListSelector = FXCollections.observableArrayList(); // Create a member list
					memberListSelector.addAll(result);
					Stage addMemberWindow = new Stage();
					addMemberWindow.setTitle("Add Member");

					AnchorPane backgroundMember = new AnchorPane();

					TextField searchField = new TextField(); // create a search field
					searchField.setPromptText("Search here!");
					searchField.setTranslateX(101);
					searchField.setTranslateY(52);
					searchField.setPrefSize(250, 26);
					searchField.setStyle(FXBACKGROUNDRADIUS);
					
					Image searchLogo = new Image("logic/Images/search--v2.png", 36, 36, true, false);
					ImageView logoView = new ImageView(searchLogo);
					logoView.setTranslateX(50);
					logoView.setTranslateY(47);
					
					Circle logo = new Circle(20, 20, 20);
					logo.setTranslateX(48);
					logo.setTranslateY(45);
					logo.setFill(javafx.scene.paint.Color.AZURE);
					
					Button addButton = new Button();
					addButton.setText("Add");
					addButton.setPrefSize(70, 40);
					addButton.setTranslateY(553);
					addButton.setTranslateX(170);
					addButton.setUnderline(true);
					addButton.setStyle(FXBACKGROUNDRADIUS);
					addButton.setUnderline(true);

					ListView<String> memberList = new ListView<>(memberListSelector); // Create a list view where I can visualize
																						// the list
					memberList.setTranslateY(96);
					memberList.setPrefSize(400, 450);
					memberList.setItems(memberListSelector);
					memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
					memberList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldVal, String newVal) -> {
						String memberSelected = memberList.getSelectionModel().getSelectedItem(); 
						//!!!!!!!!!!!!!!!!!aggiungi membro al progetto!!!!!!!!!!!!!!!!!!!!
						bean.setUserToAdd(memberSelected);
						
						addButton.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								
								projectDAO.addMemberToProject(bean, memberSelected);
								
								addMemberWindow.close();
								
								try {
									Main.getMainLayout().setCenter(FXMLLoader.load(ProjectPageView.class.getResource("Project.fxml")));
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							
						});
			
						
					});

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

					backgroundMember.getChildren().addAll(logo, logoView);
					backgroundMember.getChildren().add(searchField);
					backgroundMember.getChildren().add(memberList);
					backgroundMember.getChildren().add(addButton);
					backgroundMember.setStyle("-fx-background-color: #2d3447");

					Scene loginScene = new Scene(backgroundMember, 400, 600);
					addMemberWindow.setScene(loginScene);
					addMemberWindow.setResizable(false);
					addMemberWindow.initModality(Modality.APPLICATION_MODAL);

					addMemberWindow.show();
			}
		};
	}
	
}
