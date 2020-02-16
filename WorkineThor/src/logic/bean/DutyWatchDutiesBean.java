/**
 * Bean class that handle the communication between a Duty object and
 * the WatchDutiesView Class 
 */
package logic.bean;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.model.User;
import logic.model.Duty;
import logic.model.Duty.State;

public class DutyWatchDutiesBean {
	
	private StringProperty name;
	private StringProperty description;
	private StringProperty kindOfDuty;
	private ListProperty<User> assignedUsers;
	private ObjectProperty<State> state;

	public DutyWatchDutiesBean(Duty duty) {
		this.name = new SimpleStringProperty(duty.getName());
		this.description = new SimpleStringProperty(duty.getDescription());
		this.kindOfDuty = new SimpleStringProperty(duty.getKindOfDuty());
		this.assignedUsers = new SimpleListProperty<User>(FXCollections.observableArrayList(duty.getAssignedUsers()));
		this.state.set(duty.getState());
	
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
    
    public ArrayList<User> getAssignedUsers() {
        return (ArrayList<User>) assignedUsers.get();
    }

    public void setAssignedUsers(ArrayList<User> newAssignedUsers) {
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

	


















