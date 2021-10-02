package model;

import java.time.LocalDateTime;

public class ToDo {
	
	
	public final int ID;
	public static int globalToDoId;
	private String title;
	private String message;
	private LocalDateTime dateOfCreation;
	private LocalDateTime dueDate;
	private boolean isDone;
	
	
	//Constructors
	public ToDo() {
		this.ID = globalToDoId + 1;
		
	}
	
	public ToDo(String title, String message, LocalDateTime dueDate) {
		this.ID = globalToDoId + 1;
		this.title = title;
		this.message = message;
		this.dateOfCreation = LocalDateTime.now();
		this.dueDate = dueDate;
		this.isDone = false;
				
	}
	
	//getters
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
	
	public LocalDateTime getDateOfCreation() {
		return this.dateOfCreation;		
	}
	
	public LocalDateTime getDueDate() {
		return this.dueDate;
	}
	
	public boolean getIsDone() {
		return this.isDone;
	}
	
	//setters
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

}
