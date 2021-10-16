package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ToDo {

	// Fields
	public final int ID;
	public static int globalToDoId;
	private String title;
	private String message;
	private LocalDate dateOfCreation;
	private LocalDate dueDate;
	private boolean isDone;
	private ArrayList<String> categories;
	
	
	//Constructors
	public ToDo(String title, String message, LocalDate dueDate, String category) {
		this.ID = globalToDoId + 1;
		globalToDoId++;
		this.title = title;
		this.message = message;
		this.dateOfCreation = LocalDate.now();
		this.dueDate = dueDate;
		this.isDone = false;
		this.categories = new ArrayList<>();
		this.categories.add("Geplant");
		this.categories.add(category);
		ToDoList.categoryList.addAll(this.categories);
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
	public ArrayList<String> getCategories() {
		return this.categories;
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
	public void addCategory(String category) {
		this.categories.add(category);
	}
	
	


}
