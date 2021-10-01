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
     * Creates a new ToDo and appends it to the ToDoList
     */
    public void createToDo(ToDo toDo) {

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
