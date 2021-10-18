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
	private String category;
	private ArrayList<String> categories;
	
	
	//Constructors
	public ToDo() {
		// Empty constructor for passing the model
		this.ID = -1;
	}

	public ToDo(String title, String message, LocalDate dueDate, String category) {
		this.ID = globalToDoId + 1;
		globalToDoId++;
		this.title = title;
		this.message = message;
		this.dateOfCreation = LocalDate.now();
		this.dueDate = dueDate;
		this.isDone = false;
		this.category = category;
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
		return ToDo.globalToDoId;
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
	public String getCategory() {
		return this.category;
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
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void addCategory(String category) {
		this.categories.add(category);
	}
	
	


}
