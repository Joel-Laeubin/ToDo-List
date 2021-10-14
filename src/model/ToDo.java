package model;

import java.time.LocalDateTime;

public class ToDo {

	// Fields
	public final int ID;
	public static int globalToDoId; //counts how many ToDo's have been created
	private String title;
	private String message;
	private LocalDateTime dateOfCreation;
	private LocalDateTime dueDate;
	private boolean isDone;
	private String category;
	
	
	//Constructors
	public ToDo() {
		this.ID = globalToDoId + 1;
		
	}
	
	public ToDo(String title, String message, LocalDateTime dueDate, String category) {
		this.ID = globalToDoId + 1;
		this.title = title;
		this.message = message;
		this.dateOfCreation = LocalDateTime.now();
		this.dueDate = dueDate;
		this.isDone = false;
		this.category = category;
		ToDoList.categoryList.add(category);
				
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
	public LocalDateTime getDateOfCreation() {
		return this.dateOfCreation;		
	}
	public LocalDateTime getDueDate() {
		return this.dueDate;
	}
	public boolean getIsDone() {
		return this.isDone;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	// Setters
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	


}
