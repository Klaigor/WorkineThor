/**
 * Graphic controller of the Create project first view.
 */
package logic.workinethor.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.bean.ProjectBean;
import logic.controller.CreateProjectController;
import logic.database.ProjectDAO;
import logic.database.UserDAO;
import logic.exceptions.MemberAlreadyExistisException;
import logic.exceptions.ProjectAlreadyExistsException;
import logic.model.Session;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.workinethor.Main;

public class CreateProjectView {

	ObservableList<String> driveSelectorList = FXCollections.observableArrayList("Google Drive", "Mega", "DropBox");

	// createProject bean and controller 
	private ProjectBean bean = new ProjectBean();
	private CreateProjectController projectController = CreateProjectController.getInstace();
	
	private static final String sfond = "-fx-text-fill: #cfd1dd";
	private static final String radio = "-fx-background-radius: 10";

	// project information
	@FXML
	private TextField projectNameField;

	@FXML
	private ChoiceBox<String> driveSelector;

	@FXML
	private CheckBox driveBox;

	@FXML
	private Button next;
	
	@FXML
	private Button add;
	
	@FXML
	private BorderPane pane;
	
	@FXML
	private Label label;
	
	@FXML
	private Label labelNameProject;
	
	@FXML
	private Label labelDrive;
	
	@FXML
	private AnchorPane anchor;
	
	private boolean projectDoesNotExist = false;
	
	private ArrayList<String> allMembersToAdd = new ArrayList<>();

	//changed for test
	// changed for code smells
	@FXML
	private boolean driveBoxYes() {
		boolean checked = false;

		checked = !driveBox.isSelected();
		driveSelector.setDisable(checked);
		return true;
	}

	//changed for test
	private boolean nextYes() {
		next.setDisable(!projectDoesNotExist);
		return true;
	}

	//changed for test
	@FXML
	private boolean initialize() {
		next.setDisable(true);
		next.setText("Create");
		driveSelector.setDisable(true);
		driveSelector.setItems(driveSelectorList);
		driveSelector.setValue("");
		driveSelector.setStyle(radio);
		pane.setStyle("-fx-background-color: #2d3447");
		label.setStyle(sfond);
		labelNameProject.setStyle(sfond);
		labelDrive.setStyle(sfond);
		next.setUnderline(true);
		next.setStyle(radio);
		add.setStyle(radio);
		add.setUnderline(true);
		projectNameField.setStyle(radio);
		driveBox.setStyle(radio);	
		
		Button checkButton = new Button();
		checkButton.setText("Check Name");
		checkButton.setTranslateX(640);
		checkButton.setTranslateY(90);
		
		checkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ProjectDAO projectDAO = new ProjectDAO();
				
				if(!projectNameField.getText().isEmpty()) {
					bean.setProjectName(projectNameField.getText());
					try {
						projectDAO.checkIfProjectExist(bean);
						projectDoesNotExist = true;
					} catch (ProjectAlreadyExistsException e) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setContentText("Project Already Exists!!");
						alert.show();
						projectDoesNotExist = false;
					}
					nextYes();
				}
			}
		});
		
		
		anchor.getChildren().add(checkButton);
		
		return true;
	}

	//changed for test
	@FXML
	private boolean goNext() throws IOException, InterruptedException {
		boolean result = false;
		BorderPane mainLayout = null;
		mainLayout = Main.getMainLayout();

		// pass createProject values to the bean class
		bean.setProjectName(projectNameField.getText());
		if (driveBox.isSelected() && driveSelector.getValue() != "") {
			bean.setDriveIsActive(true);
			bean.setDriveName(driveSelector.getValue());
		}

		// pass bean to controller so that i can instantiate a new project(model)
		try {
			projectController.createProject(bean);
			for(String member: allMembersToAdd) {
				projectController.addMember(member);
			}
			result = true;
		} catch (ProjectAlreadyExistsException e1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Project Already Exists!!");
			alert.show();
			result = false;
		}

		if(result) {
			
			Session.getSession().setCurrentBrowsingProject(bean);
			BorderPane mainLayoutNext = null;
			try {
				mainLayoutNext = FXMLLoader.load(NavBarView.class.getResource("Project.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainLayout.setCenter(mainLayoutNext);
		}
		return true;
	}

	//changed for test
	// method that create the add member view
	@FXML
	private boolean addMember() throws SQLException {
		UserDAO usrDAO = new UserDAO();
		ObservableList<String> result = usrDAO.getAllUsers();
		ObservableList<String> memberListSelector = FXCollections.observableArrayList(); // Create a member list
		Stage addMemberFinestra = new Stage();
		addMemberFinestra.setTitle("Add Member");

		AnchorPane back = new AnchorPane();

		TextField barraRicerca = new TextField(); // create a search field
		barraRicerca .setPromptText("Search here!");
		barraRicerca .setTranslateX(101);
		barraRicerca .setTranslateY(52);
		barraRicerca .setPrefSize(250, 26);
		barraRicerca .setStyle(radio);
		
		Image lente = new Image("logic/Images/search--v2.png", 36, 36, true, false);
		ImageView View = new ImageView(lente);
		View.setTranslateX(50);
		View.setTranslateY(47);
		
		Circle shape = new Circle(20, 20, 20);
		shape.setTranslateX(48);
		shape.setTranslateY(45);
		shape.setFill(javafx.scene.paint.Color.AZURE);
		
		Button add = new Button();
		add.setText("Add");
		add.setPrefSize(70, 40);
		add.setTranslateY(553);
		add.setTranslateX(170);
		add.setUnderline(true);
		add.setStyle(radio);

		ListView<String> member = new ListView<>(memberListSelector); // Create a list view where I can visualize
																			// the list
		member.setTranslateY(96);
		member.setPrefSize(400, 450);
		member.setItems(memberListSelector);
		member.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		member.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldVal, String newVal) -> {
			String memberSelected = member.getSelectionModel().getSelectedItem(); 
			//!!!!!!!!!!!!!!!!!aggiungi membro al progetto!!!!!!!!!!!!!!!!!!!!
			bean.setUserToAdd(memberSelected);
			
			add.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					boolean result = false;
					try {
						result = validateMember(memberSelected);
					} catch (MemberAlreadyExistisException e) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setContentText("Member already Selected!!");
						alert.show();
					}
					
					if(!result) {
						addMemberFinestra.close();
					}
				}
				
			});
		});
		
		memberListSelector.addAll(result);

		FilteredList<String> filteredData = new FilteredList<>(memberListSelector, s -> true); // create a filtered
																								// member list
		barraRicerca .textProperty().addListener(obs -> { // Compare if in the list there are some equals with the
														// filtered list
			String filter = barraRicerca.getText();
			if (filter == null || filter.length() == 0) {
				filteredData.setPredicate(s -> true);
			} else {
				filteredData.setPredicate(s -> s.contains(filter));
			}
			member.setItems(filteredData); // show filtered list
		});

		back.getChildren().addAll(shape, View);
		back.getChildren().add(barraRicerca);
		back.getChildren().add(member);
		back.getChildren().add(add);
		back.setStyle("-fx-background-color: #2d3447");

		Scene loginScene = new Scene(back, 400, 600);
		addMemberFinestra.setScene(loginScene);
		addMemberFinestra.setResizable(false);
		addMemberFinestra.initModality(Modality.APPLICATION_MODAL);

		addMemberFinestra.show();
		
		return true;
	}

	public boolean validateMember(String memberSelected) throws MemberAlreadyExistisException {
		boolean result = false;
		for(String member: allMembersToAdd) {
			if(memberSelected.equals(member))
				result = true;
		}
		
		if(!result) {
			allMembersToAdd.add(memberSelected);
		}
		else {
			throw new MemberAlreadyExistisException("Member already in queue");
		}
		
		return result;
	}
}
