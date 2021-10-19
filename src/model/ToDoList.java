package model;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToDoList {

	// Fields
	private ArrayList <ToDo> toDoList = new ArrayList<>();
	protected static ArrayList<String> categoryList = new ArrayList<>(); //contains all categorys of active ToDo's
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
	public void addToDo(ToDo toDo) {
		this.toDoList.add(toDo);
	}
	public void removeToDo(ToDo toDo) { 
		this.toDoList.remove(toDo);
		ToDo.globalToDoId -= 1;
		ToDoList.categoryList.remove(toDo.getCategories());
		
	}
	public int getNumberOfCategoryTypes() {
		HashSet<String> uniqueValues = new HashSet<>(ToDoList.categoryList);
		int uniqueTypes = uniqueValues.size();
		return uniqueTypes;
		
	}

	// Get subsets of toDos
	public ObservableList<ToDo> getToDoListImportant() {
		String category = "Wichtig";
		for (ToDo toDo : this.toDoList) {
			if (toDo.getCategory().equals(category)) {
			this.importantList.add(toDo);
			}
		}
		return this.importantList;
	}
	public ObservableList<ToDo> getToDoListPlanned() {
		String category = "Geplant";
		for (ToDo toDo : this.toDoList) {
			if (toDo.getCategory().equals(category)) {
			this.plannedList.add(toDo);
			}
		}
		return this.plannedList;
	}
	public  ObservableList<ToDo> getToDoListDone() {
		String category = "Erledigt";
		for (ToDo toDo : this.toDoList) {
			if (toDo.getCategory().equals(category)) {
			this.doneList.add(toDo);
			}
		}
		return this.doneList;
	}
	public  ObservableList<ToDo> getToDoListGarbage() {
		String category = "Papierkorb";
		for (ToDo toDo : this.toDoList) {
			if (toDo.getCategory().equals(category)) {
			this.garbageList.add(toDo);
			}
		}
		return this.garbageList;
	}
}
