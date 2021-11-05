package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class ToDo {

	// Fields
	public final int ID;
	public static int globalToDoId = 0;
	private String title;
	private String message;
	private LocalDate dateOfCreation;
	private LocalDate dueDate;
	private String dueDateString;
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
		this.dueDateString = this.dueDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		this.isDone = false;

		this.categories = new ArrayList<>();
		this.categories.add("Geplant");
		this.categories.add(category);

		// Sort out categories using a set
		Set<String> categorySet = new HashSet<String>(this.categories);
		this.categories.clear();
		this.categories.addAll(categorySet);
		ToDoList.categoryList.addAll(this.categories);

		// Select other category than "Geplant" if available
		this.category = "Geplant";
		for(String cat : this.categories) {
			if(!cat.equals("Geplant")) { this.category = cat; }
		}
		
		this.doneButton = new Button();
		ImageView done = new ImageView("/icons/doneIcon3.png");
		done.setFitHeight(20);
		done.setFitWidth(20);
		this.doneButton.setGraphic(done);
		
		this.garbageButton = new Button();
		ImageView garbage = new ImageView("/icons/garbageIcon2.png");
		garbage.setFitHeight(20);
		garbage.setFitWidth(20);
		this.garbageButton.setGraphic(garbage);
	
		this.importantButton = new Button();
		ImageView important = new ImageView("/icons/starIcon2.png");
		important.setFitHeight(20);
		important.setFitWidth(20);
		this.importantButton.setGraphic(important);
		
		this.doneButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		this.garbageButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		this.importantButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		this.doneButton.getStyleClass().add("button");
		this.garbageButton.getStyleClass().add("button");
		this.importantButton.getStyleClass().add("button");
	}

	public ToDo(String title, String message, LocalDate dueDate, String category, ArrayList<String> tags) {
		this.ID = globalToDoId + 1;
		globalToDoId++;
		this.title = title;
		this.message = message;
		this.dateOfCreation = LocalDate.now();
		this.dueDate = dueDate;
		this.dueDateString = this.dueDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		this.isDone = false;
		this.tags = tags;

		this.categories = new ArrayList<>();
		this.categories.add("Geplant");
		this.categories.add(category);

		// Sort out categories using a set
		Set<String> categorySet = new HashSet<String>(this.categories);
		this.categories.clear();
		this.categories.addAll(categorySet);
		ToDoList.categoryList.addAll(this.categories);

		// Select other category than "Geplant" if available
		this.category = "Geplant";
		for(String cat : this.categories) {
			if(!cat.equals("Geplant")) { this.category = cat; }
		}

		this.doneButton = new Button();
		ImageView done = new ImageView("/icons/doneIcon3.png");
		done.setFitHeight(20);
		done.setFitWidth(20);
		this.doneButton.setGraphic(done);
		
		this.garbageButton = new Button();
		ImageView garbage = new ImageView("/icons/garbageIcon2.png");
		garbage.setFitHeight(20);
		garbage.setFitWidth(20);
		this.garbageButton.setGraphic(garbage);
	
		this.importantButton = new Button();
		ImageView important = new ImageView("/icons/starIcon2.png");
		important.setFitHeight(20);
		important.setFitWidth(20);
		this.importantButton.setGraphic(important);
		this.doneButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		this.garbageButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		this.importantButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		this.doneButton.getStyleClass().add("button");
		this.garbageButton.getStyleClass().add("button");
		this.importantButton.getStyleClass().add("button");
	}

	// Constructor used by the db-handler
	public ToDo(String title, String message, LocalDate dateOfCreation,
				LocalDate dueDate, ArrayList<String> categories, ArrayList<String> tags) {

		this.ID = globalToDoId + 1;
		ToDo.globalToDoId++;
		this.title = title;
		this.message = message;
		this.dateOfCreation = dateOfCreation;
		this.dueDate = dueDate;
		this.dueDateString = this.dueDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		this.isDone = false;

		this.categories = categories;
		// Parse out category if not "Geplant". Since only 1 other category can be selected, we can use a simple for loop
		this.category = "Geplant";
		for (String category : categories) {
			if (!category.equals("Geplant")) {
				this.category = category.replace(" ", "");
			}
		}

		if(!this.categories.contains("Geplant")) {
			this.categories.add("Geplant");
		}
		ToDoList.categoryList.addAll(this.categories);
		this.tags = tags;


		this.doneButton = new Button();
		ImageView done = new ImageView("/icons/doneIcon3.png");
		done.setFitHeight(20);
		done.setFitWidth(20);
		this.doneButton.setGraphic(done);
		
		this.garbageButton = new Button();
		ImageView garbage = new ImageView("/icons/garbageIcon2.png");
		garbage.setFitHeight(20);
		garbage.setFitWidth(20);
		this.garbageButton.setGraphic(garbage);
	
		this.importantButton = new Button();
		ImageView important = new ImageView("/icons/starIcon2.png");
		important.setFitHeight(20);
		important.setFitWidth(20);
		this.importantButton.setGraphic(important);

		this.doneButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		this.garbageButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		this.importantButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		
	}

	// Constructor used to update an item
	public ToDo(int ID, String title, String message, LocalDate dueDate, LocalDate dateOfCreation,
				String category, ArrayList<String> tags, boolean update) {

		if(update) {
			this.ID = ID;
			this.title = title;
			this.message = message;
			this.dateOfCreation = dateOfCreation;
			this.dueDate = dueDate;
			this.dueDateString = this.dueDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			this.isDone = false;
			this.tags = tags;

			this.categories = new ArrayList<>();
			this.categories.add("Geplant");
			this.categories.add(category);

			// Sort out categories using a set
			Set<String> categorySet = new HashSet<String>(this.categories);
			this.categories.clear();
			this.categories.addAll(categorySet);
			ToDoList.categoryList.addAll(this.categories);

			// Select other category than "Geplant" if available
			this.category = "Geplant";
			for(String cat : this.categories) {
				if(!cat.equals("Geplant")) { this.category = cat; }
			}

			this.doneButton = new Button();
			ImageView done = new ImageView("/icons/doneIcon2.png");
			done.setFitHeight(20);
			done.setFitWidth(20);
			this.doneButton.setGraphic(done);

			this.garbageButton = new Button();
			ImageView garbage = new ImageView("/icons/garbageIcon2.png");
			garbage.setFitHeight(20);
			garbage.setFitWidth(20);
			this.garbageButton.setGraphic(garbage);

			this.importantButton = new Button();
			ImageView important = new ImageView("/icons/starIcon2.png");
			important.setFitHeight(20);
			important.setFitWidth(20);
			this.importantButton.setGraphic(important);

			this.doneButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
			this.garbageButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
			this.importantButton.getStylesheets().add(getClass().getResource("ToDoButtonsStyle.css").toExternalForm());
		} else {this.ID = -1; }


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
	public String getDueDateString() {
		return dueDateString;
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
	public void setDueDateString(String dueDateString) {
		this.dueDateString = dueDateString;
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
