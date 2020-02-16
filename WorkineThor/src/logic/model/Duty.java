package logic.model;

import java.util.ArrayList;

public class Duty {
		public enum State {
		discovered,
		underDevelopement,
		done,
	}
	private String name;
	private String description;
	//this could be further developed 
	private String kindOfDuty;
	private ArrayList<User> assignedUsers;
	private State state;
	
	public String getName() {
		return this.name;
	}
	
	public boolean setName(String newName) {
		this.name = newName;
		return true;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public boolean setDescription(String newDescription) {
		this.description = newDescription;
		return true;
	}
	
	public String getKindOfDuty() {
		return this.kindOfDuty;
	}
	
	public boolean setKindOfDuty(String newKindOfDuty) {
		this.kindOfDuty = newKindOfDuty;
		return true;
	}
	
	public ArrayList<User> getAssignedUsers() {
		return this.assignedUsers;
	}
	
	public boolean setState(String newDescription) {
		this.description = newDescription;
		return true;
	}
	
	public State getState() {
		return this.state;
	}
	
	public boolean setState(State newState) {
		this.state = newState;
		return true;
	}
	
}