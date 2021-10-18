package model;

import java.util.ArrayList;
import java.util.HashSet;

public class ToDoList {

	// Fields
	private ArrayList <ToDo> toDoList = new ArrayList<>();
	protected static ArrayList<String> categoryList = new ArrayList<>(); //contains all categorys of active ToDo's
	
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
	
	public ToDo getToDoListImportant() {
		ToDo returnImportant = null;
		
		for (ToDo toDo : this.toDoList) {	
			if (ToDoList.categoryList.contains("Wichtig")) {
				returnImportant = toDo;
				break;
			}
		}
		return returnImportant;
	}

	public ToDo getToDoListPlanned() {
		ToDo returnPlanned = null;
		
		for (ToDo toDo : this.toDoList) {	
			if (ToDoList.categoryList.contains("Geplant")) {
				returnPlanned = toDo;
				break;
			}
		}
		return returnPlanned;
	}
	
	public ToDo getToDoListDone() {
		ToDo returnDone = null;
		
		for (ToDo toDo : this.toDoList) {	
			if (ToDoList.categoryList.contains("Erledigt")) {
				returnDone = toDo;
				break;
			}
		}
		return returnDone;
	}
	
	public ToDo getToDoListGarbage() {
		ToDo returnGarbage = null;
		
		for (ToDo toDo : this.toDoList) {	
			if (ToDoList.categoryList.contains("Erledigt")) {
				returnGarbage = toDo;
				break;
			}
		}
		return returnGarbage;
	
	
	}
}
