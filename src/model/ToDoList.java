package model;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToDoList {

	// Fields
	private static ArrayList <ToDo> toDoList = new ArrayList<>();
	protected static ArrayList<String> categoryList = new ArrayList<>(); //contains all categorys of active ToDo's
	private static ObservableList<ToDo> importantList = FXCollections.observableArrayList();
	private static ObservableList<ToDo> plannedList = FXCollections.observableArrayList();
	private static ObservableList<ToDo> doneList = FXCollections.observableArrayList();
	private static ObservableList<ToDo> garbageList = FXCollections.observableArrayList();
	
	//potential list to separate categorys between done ToDo-Objects and undone
	//protected static ArrayList<String> categoryListDoneObjects = new ArrayList<>();

	// getter
	public ArrayList <ToDo> getToDoList(){
		return ToDoList.toDoList;
	}
	
	//retrieves a specific ToDo object from the toDoList
	public ToDo getToDo(int ID) {
		ToDo returnVal = null;

		for (ToDo toDo : ToDoList.toDoList) {
			if (toDo.getID() == ID) {
				returnVal = toDo;
				break;
			}
		}

		return returnVal;
	}

	public void addToDo(ToDo toDo) {
		ToDoList.toDoList.add(toDo);
	}
		
	
	public void removeToDo(ToDo toDo) { 
		ToDoList.toDoList.remove(toDo); 
		ToDo.globalToDoId -= 1;
		ToDoList.categoryList.remove(toDo.getCategories());
		
	}
	
	
	public int getNumberOfCategoryTypes() {
		HashSet<String> uniqueValues = new HashSet<>(ToDoList.categoryList);
		int uniqueTypes = uniqueValues.size();
		return uniqueTypes;
		
	}
	
	public ObservableList<ToDo> getToDoListImportant() {
		String category = "Wichtig";
		for (ToDo toDo : ToDoList.toDoList) {	
			if (toDo.getCategory().equals(category)) {
			ToDoList.importantList.add(toDo);
			}
		}
		return ToDoList.importantList;
	}

	public ObservableList<ToDo> getToDoListPlanned() {
		String category = "Geplant";
		for (ToDo toDo : ToDoList.toDoList) {	
			if (toDo.getCategory().equals(category)) {
			ToDoList.plannedList.add(toDo);
			}
		}
		return ToDoList.plannedList;
	}
	
	public  ObservableList<ToDo> getToDoListDone() {
		String category = "Erledigt";
		for (ToDo toDo : ToDoList.toDoList) {	
			if (toDo.getCategory().equals(category)) {
			ToDoList.doneList.add(toDo);
			}
		}
		return ToDoList.doneList;
	}
	public  ObservableList<ToDo> getToDoListGarbage() {
		String category = "Papierkorb";
		for (ToDo toDo : ToDoList.toDoList) {	
			if (toDo.getCategory().equals(category)) {
			ToDoList.garbageList.add(toDo);
			}
		}
		return ToDoList.garbageList;
	}
}
