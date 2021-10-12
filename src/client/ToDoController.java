package client;

import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import model.ToDo;
import model.ToDoList;
import client.ToDoView;

import java.time.LocalDateTime;
import java.util.Optional;

public class ToDoController {

    // Fields
    private ToDoView toDoView;
    private ToDo toDo;
    private ToDoList toDoList;

    // Constructor
    public ToDoController(ToDoView toDoView, ToDo toDo, ToDoList toDoList) {
        this.toDoView = toDoView;
        this.toDo = toDo;
        this.toDoList = toDoList;

        // Register buttons

        this.toDoView.listView.setOnMouseClicked(this::changeCenterBar);
    }


    // ------- CRUD-Methods
    /* Create method
     * Parses the inputs of the user required for a new ToDoInstance, creates the instance and stores it.
     * Needs input from ToDoView
     */
    public void createToDo(String title, String message, LocalDateTime dateOfCreation, LocalDateTime dueDate) {

    }

    /* Read method
    * Returns a ToDo based on its ID
     */
    public ToDo getToDo(int ID) {
        return this.toDoList.getToDo(ID);
    }

    /* Update method
     * Gets a specific ToDo based on its ID, updated the contents and stores it again.
     * Maybe pass in an ToDo as parameter?
     */
    public void updateToDo(int ID, String title, String message, LocalDateTime dueDate) {

        // Fetch item & old status
        ToDo itemToUpdate = this.toDoList.getToDo(ID);
        String oldTitle = itemToUpdate.getTitle();
        String oldMessage = itemToUpdate.getMessage();
        LocalDateTime oldDueDate = itemToUpdate.getDueDate();

        // Delete old item from arrayList
        this.toDoList.getToDoList().remove(itemToUpdate);

        // Compare changes
        boolean titleChanged = oldTitle.equals(title);
        boolean messageChanged = oldMessage.equals(message);
        boolean dueDateChanged = oldDueDate == dueDate;

        // Make changes
        if (titleChanged) { itemToUpdate.setTitle(title); }
        if (messageChanged) { itemToUpdate.setMessage(message); }
        if (dueDateChanged) { itemToUpdate.setDueDate(dueDate); }

        // Insert changed item into ArrayList
        this.toDoList.addToDo(itemToUpdate);
    }

    /* Delete method
     * Gets a specific ToDo based on its ID and deletes it.
     * Maybe pass in an ToDo as parameter?
     */
    public void deleteToDo(int ID) {

        // Fetch ToDo item
        ToDo itemToRemove = this.toDoList.getToDo(ID);
        this.toDoList.removeToDo(itemToRemove);
    }

    // Dialog box method
    public void createToDoDialog(MouseEvent e) {

        // Show dialog
        Optional<ButtonType> result = this.toDoView.addToDoDialog.showAndWait();

        // Parse result

        // Create toDo

        // Add ToDo

    }


	private void changeCenterBar(MouseEvent e) {
		switch (toDoView.listView.getSelectionModel().getSelectedIndex()) {
		case 0:
			ImportantBarView important = new ImportantBarView();
	        important.createToDo.setOnMouseClicked(this::createToDoDialog);
			toDoView.setCenter(new ImportantBarView());
			break;
		case 1:
			toDoView.setCenter(new PlannedBarView());
			break;
		case 2:
			toDoView.setCenter(new DoneBarView());
			break;
		case 3:
			toDoView.setCenter(new GarbageBarView());
		}
		
	}
    
    

}
