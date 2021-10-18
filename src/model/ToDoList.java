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
}
