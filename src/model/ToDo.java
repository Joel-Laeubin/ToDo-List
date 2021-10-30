package model;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

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
	private ImageView importantIcon;
	private ImageView doneIcon;
	private ImageView garbageIcon;	
	private Button importantButton;
	private Button doneButton;
	private Button garbageButton;
	private ArrayList<String> tags;
	
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
		
		this.importantButton = new Button();
		this.importantIcon = new ImageView("/icons/starIcon.png");
		this.importantButton.setGraphic(importantIcon);
		
		this.doneButton = new Button();
		this.doneIcon = new ImageView("/icons/doneIcon2.png");
		this.doneButton.setGraphic(doneIcon);
		
		this.garbageButton = new Button();
		this.garbageIcon = new ImageView("/icons/garbageIcon.png");
		this.garbageButton.setGraphic(garbageIcon);

		
		this.categories.add("Geplant");
		this.categories.add(category);
		ToDoList.categoryList.addAll(this.categories);
	}

	public ToDo(String title, String message, LocalDate dueDate, String category, ArrayList<String> tags) {
		this.ID = globalToDoId + 1;
		globalToDoId++;
		this.title = title;
		this.message = message;
		this.dateOfCreation = LocalDate.now();
		this.dueDate = dueDate;
		this.isDone = false;

		this.category = category;
		this.categories = new ArrayList<>();

		this.importantButton = new Button();
		this.importantIcon = new ImageView("/icons/starIcon.png");
		this.importantButton.setGraphic(importantIcon);
		
		this.doneButton = new Button();
		this.doneIcon = new ImageView("/icons/doneIcon2.png");
		this.doneButton.setGraphic(doneIcon);
		
		this.garbageButton = new Button();
		this.garbageIcon = new ImageView("/icons/garbageIcon.png");
		this.garbageButton.setGraphic(garbageIcon);

		this.categories.add("Geplant");
		this.categories.add(category);
		this.category = category;
		ToDoList.categoryList.addAll(this.categories);


		this.tags = tags;
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
	public Button getDoneButton() {
		return this.doneButton;
	}
	public Button getImportantButton() {
		return this.importantButton;
	}
	public Button getGarbageButton() {
		return this.garbageButton;
	}
	public String getCategory() {
		return this.category;
	}
	public ArrayList<String> getCategories() {
		return this.categories;
	}
	public ArrayList<String> getTags() { return this.tags; }

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

		// Remove old category
		String oldCategory = this.getCategory();
		this.categories.remove(oldCategory);

		// If category is set on 'done' or 'deleted', remove all other categories since those states are absolute
		if(category.equals("Erledigt") || category.equals("Papierkorb")) {
			this.categories.clear();
			this.categories.add(category);
		}

		this.category = category;
	}

	public void setDoneButton(Button doneButton) {
		this.doneButton = doneButton;
	}
	public void setImportantButton(Button importantButton) {
		this.importantButton = importantButton;
	}
	public void setGarbageButton(Button garbageButton) {
		this.garbageButton = garbageButton;
	}
	public void addCategory(String category) {
		this.categories.add(category);	
	}
	public void setTags(ArrayList<String> tags) { this.tags = tags; }

}
