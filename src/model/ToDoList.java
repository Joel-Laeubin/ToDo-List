package model;

import java.util.ArrayList;

public class ToDoList {
	
	private ArrayList <ToDo> toDoList = new ArrayList<>();
	
	public ArrayList <ToDo> getToDoList(){
		return this.toDoList;
	}
	
	public void addToDo(ToDo toDo) {
		this.toDoList.add(toDo);
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
}
