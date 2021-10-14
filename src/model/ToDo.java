package model;

import java.time.LocalDate;

public class ToDo {

	// Fields
	public final int ID;
	public static int globalToDoId;
	private String title;
	private String message;
	private LocalDate dateOfCreation;
	private LocalDate dueDate;
	private boolean isDone;
	
	
	//Constructors
	public ToDo() {
		this.ID = globalToDoId + 1;
		
	}
	
	public ToDo(String title, String message, LocalDate dueDate) {
		this.ID = globalToDoId + 1;
		this.title = title;
		this.message = message;
		this.dateOfCreation = LocalDate.now();
		this.dueDate = dueDate;
		this.isDone = false;
				
	}
	
	// Getters
	public int getID() {
		return this.ID;
	}
	public int getGlobalToDoId() {
		return this.globalToDoId;
	}
	public String getTitle() {
		return this.title;
	}
	public String getMessage() {
		return this.message;
	}
	public LocalDate getDateOfCreation() {
		return this.dateOfCreation;		
	}
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	public boolean getIsDone() {
		return this.isDone;
	}
	
	// Setters
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	


}
