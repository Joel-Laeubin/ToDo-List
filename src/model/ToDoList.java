package model;

import java.util.ArrayList;

public class ToDoList {

	// Fields
	private ArrayList <ToDo> toDoList = new ArrayList<>();

	// Methods
	public ArrayList <ToDo> getToDoList(){
		return this.toDoList;
	}
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
			
	}
}
