package model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class ToDoList {

	// Fields
	private ArrayList <ToDo> toDoList = new ArrayList<>();
	public static ArrayList<String> categoryList = new ArrayList<>(); //contains all categorys of active ToDo's
	private ObservableList<ToDo> importantList = FXCollections.observableArrayList();
	private ObservableList<ToDo> plannedList = FXCollections.observableArrayList();
	private ObservableList<ToDo> doneList = FXCollections.observableArrayList();
	private ObservableList<ToDo> garbageList = FXCollections.observableArrayList();
	
	//potential list to separate categorys between done ToDo-Objects and undone
	//protected static ArrayList<String> categoryListDoneObjects = new ArrayList<>();

	// getter
	public ArrayList <ToDo> getToDoList(){
		return this.toDoList;
	}
	
	//retrieves a specific ToDo object from the toDoList
	public ToDo getToDo(int ID) {
		ToDo returnVal = null;

		for (ToDo toDo : this.toDoList) {
			if (toDo.getID() == ID) {
				returnVal = toDo;
				break;
			}
		}

		return returnVal;
	}

	// Additional getToDo method based on the button of the ToDo
	// Used in controller to link mouseClickEvents
	public ToDo getToDo(Button button) {
		ToDo returnVal = null;

		for(ToDo toDo : this.toDoList) {
			if(toDo.getDoneButton() == button) { returnVal = toDo; break; }
			if(toDo.getGarbageButton() == button) { returnVal = toDo; break; }
			if(toDo.getImportantButton() == button) { returnVal = toDo; break; }
		}

		return returnVal;
	}

	public void addToDo(ToDo toDo) {
		this.toDoList.add(toDo);

		// Adding the ToDo to a subset, depending on its category.
		switch(toDo.getCategory()) {
			case "Wichtig":
				this.importantList.add(toDo);
				break;
			case "Geplant":
				this.plannedList.add(toDo);
				break;
			case "Erledigt":
				this.doneList.add(toDo);
				break;
			case "Papierkorb":
				this.garbageList.add(toDo);
		}

	}

	// By updating an item we don't change the global ID counter
	public void updateToDo(ToDo oldItem, ToDo newItem) {
		for(ToDo item : this.toDoList) {
			if(item.getID() == oldItem.getID()) {
				item.setTitle(newItem.getTitle());
				item.setMessage(newItem.getMessage());
				item.setDueDate(newItem.getDueDate());
				item.setDueDateString(item.getDueDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
				item.setCategory(newItem.getCategory());
				item.setTags(newItem.getTags());
			}
		}
	}

	/* Method to refresh the contents of each
	 *
	 */
	public void updateSublists() {

		this.importantList.clear();
		this.plannedList.clear();
		this.doneList.clear();
		this.garbageList.clear();

		for(ToDo toDo : this.toDoList) {
			switch (toDo.getCategory()) {
				case "Wichtig" -> { this.importantList.add(toDo); this.plannedList.add(toDo); }
				case "Geplant" -> this.plannedList.add(toDo);
				case "Erledigt" -> this.doneList.add(toDo);
				case "Papierkorb" -> this.garbageList.add(toDo);
			}
		}
	}

	/* Method to search for items via the searchbar
	 * Looks into title & message of each item
	 */
	public ArrayList<ToDo> searchItem(String searchString) {

		ArrayList<ToDo> returnList = new ArrayList<>();
		for(ToDo toDo : this.toDoList) {
			if (toDo.getTitle().contains(searchString) || toDo.getMessage().contains(searchString)) {
				returnList.add(toDo);
			}
		}
		return returnList;
	}

	public ArrayList<ToDo> searchLocalToday() {
		
		LocalDate now = LocalDate.now();
		
		ArrayList<ToDo> returnToday = new ArrayList<>();	
		for(ToDo toDo : this.toDoList) {
			if (toDo.getDueDate().equals(now)) {
				returnToday.add(toDo);
			}
		}
		return returnToday;
	}
	
	public ArrayList<ToDo> searchLocalWeek() {
		
		LocalDate now = LocalDate.now();
		LocalDate inAWeek = LocalDate.now().plusDays(7);
		
		ArrayList<ToDo> returnWeek = new ArrayList<>();
		for(ToDo toDo : this.toDoList) {
			if (toDo.getDueDate().compareTo(now) >= 0 && toDo.getDueDate().compareTo(inAWeek) >= 1) {
				returnWeek.add(toDo);
			}
		}
		return returnWeek;		
	}
	
	public ArrayList<ToDo> searchLocalMonth() {
	
		LocalDate now = LocalDate.now();
		LocalDate inAMonth = LocalDate.now().plusDays(30);
		
		ArrayList<ToDo> returnMonth = new ArrayList<>();
		for(ToDo toDo : this.toDoList) {
			if (toDo.getDueDate().compareTo(now) >= 0 && toDo.getDueDate().compareTo(inAMonth) >= 1) {
				returnMonth.add(toDo);
			}
		}
		return returnMonth;		
	}
	
	public void removeToDo(ToDo toDo) { 
		this.toDoList.remove(toDo);
		ToDo.globalToDoId -= 1;
		ToDoList.categoryList.remove(toDo.getCategory());

		// Delete item from all sublists

		
	}
	public int getNumberOfCategoryTypes() {
		HashSet<String> uniqueValues = new HashSet<>(ToDoList.categoryList);
		int uniqueTypes = uniqueValues.size();
		return uniqueTypes;
		
	}
		
	public int getDoneNumber() {
		int doneCount = 0;		
		for (String category : ToDoList.categoryList) {
			if(category.equals("Erledigt")) {
				doneCount++;
			}
		}
		return doneCount;		
	}

	// Get subsets of toDos
	public ObservableList<ToDo> getToDoListImportant() { return this.importantList; }
	public ObservableList<ToDo> getToDoListPlanned() { return this.plannedList; }
	public ObservableList<ToDo> getToDoListDone() { return this.doneList; }
	public ObservableList<ToDo> getToDoListGarbage() { return this.garbageList; }

	public ArrayList<ToDo> getImportantList() {
		ArrayList<ToDo> resultSet = new ArrayList<>();
		resultSet.addAll(this.getToDoListImportant());
		return resultSet;
	}
	public ArrayList<ToDo> getPlannedList() {
		ArrayList<ToDo> resultSet = new ArrayList<>();
		resultSet.addAll(this.getToDoListPlanned());
		return resultSet;
	}
	public ArrayList<ToDo> getDoneList() {
		ArrayList<ToDo> resultSet = new ArrayList<>();
		resultSet.addAll(this.getToDoListDone());
		return resultSet;
	}
	public ArrayList<ToDo> getGarbageList() {
		ArrayList<ToDo> resultSet = new ArrayList<>();
		resultSet.addAll(this.getToDoListGarbage());
		return resultSet;
	}

}
