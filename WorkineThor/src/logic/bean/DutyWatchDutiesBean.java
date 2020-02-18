/**
 * Bean class that handle the communication between a Duty object and
 * the WatchDutiesView Class 
 */
package logic.bean;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import logic.model.User;
import logic.model.Duty;
import logic.model.Duty.State;

public class DutyWatchDutiesBean {
	
	private StringProperty name;
	private StringProperty project;
	private ListProperty<User> assignedUsers;
	private StringProperty kindOfDuty;
	private StringProperty description;	
	private ObjectProperty<State> state;

	public DutyWatchDutiesBean(Duty duty) {
		this.name = new SimpleStringProperty(duty.getName());
		this.description = new SimpleStringProperty(duty.getDescription());
		this.kindOfDuty = new SimpleStringProperty(duty.getKindOfDuty());
		if (duty.getState()!=null) {
			this.state.set(duty.getState());
		}
		if (duty.getAssignedUsers()!=null) {
			this.assignedUsers = new SimpleListProperty<>(FXCollections.observableArrayList(duty.getAssignedUsers()));
		}
	}
	
	
	
	public String getName(){
    	return name.get();
    }
    
    public void setName(String newName) {
    	this.name.set(newName);
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
	public String getProject(){
    	return project.get();
    }
    
    public void setProject(String newProject) {
    	this.project.set(newProject);
    }
    
    public StringProperty projectProperty() {
        return project;
    }
    
	public String getDescription(){
    	return description.get();
    }
    
    public void setDescription(String newDescription) {
    	this.description.set(newDescription);
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }
    
	public String getKindOfDuty(){
    	return kindOfDuty.get();
    }
    
    public void setKindOfDuty(String newKindOfDuty) {
    	this.kindOfDuty.set(newKindOfDuty);
    }
    
    public StringProperty kindOfDutyProperty() {
        return kindOfDuty;
    }
    
    public List<User> getAssignedUsers() {
        return (List<User>) assignedUsers.get();
    }

    public void setAssignedUsers(List<User> newAssignedUsers) {
        this.assignedUsers.set((ObservableList<User>) newAssignedUsers);
    }
    
    public ListProperty<User> assignedUsersProperty() {
        return assignedUsers;
    }
    
    public State getState() {
        return state.get();
    }

    public void setState(State state) {
        this.state.set(state);
    }
    
    public ObjectProperty stateProperty() {
        return state;
    }
}

	


















