package controller;

import java.time.LocalDateTime;

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

        // Register CRUD-events

    }

    // ------- CRUD-Methods
    /* Create method
     * Parses the inputs of the user required for a new ToDoInstance, creates the instance and stores it.
     */
    public void createToDo(String title, String message, LocalDateTime dateOfCreation, LocalDateTime dueDate,) {

    }

    /* Read method
    * Returns a ToDo based on its ID
     */
    public ToDo getToDo(int ID) {

    }

    /* Update method
     * Gets a specific ToDo based on its ID, updated the contents and stores it again.
     * Maybe pass in an ToDo as parameter?
     */
    public void updateToDo(int ID, String title, String message, LocalDateTime dueDate, LocalDateTime dateOfCreation) {

    }

    /* Update method
     * Gets a specific ToDo based on its ID and deletes it.
     * Maybe pass in an ToDo as parameter?
     */
    public void deleteToDo(int ID) {

    }

}
