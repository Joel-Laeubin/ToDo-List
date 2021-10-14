package client;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import model.ToDo;
import model.ToDoList;
import client.ToDoView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.time.LocalDate;

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
    public void createToDo(String title, String message, LocalDate dueDate, String category) {
        this.toDoList.addToDo(new ToDo(title, message, dueDate, category));
        System.out.println("Added new ToDo to the arrayList");
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
    public void updateToDo(int ID, String title, String message, LocalDate dueDate) {

        // Fetch item & old status
        ToDo itemToUpdate = this.toDoList.getToDo(ID);
        String oldTitle = itemToUpdate.getTitle();
        String oldMessage = itemToUpdate.getMessage();
        LocalDate oldDueDate = itemToUpdate.getDueDate();

        // Delete old item from arrayList
        this.toDoList.getToDoList().remove(itemToUpdate);

        // Compare changes
        boolean titleChanged = oldTitle.equals(title);
        boolean messageChanged = oldMessage.equals(message);
        boolean dueDateChanged = oldDueDate == dueDate;

        // Make changes
        if (titleChanged) {
            itemToUpdate.setTitle(title);
        }
        if (messageChanged) {
            itemToUpdate.setMessage(message);
        }
        if (dueDateChanged) {
            itemToUpdate.setDueDate(dueDate);
        }

        // Insert changed item into ArrayList
        this.toDoList.addToDo(itemToUpdate);
    }

    /* Delete method
     * Gets a specific ToDo based on its ID and deletes it.
     */
    public void deleteToDo(int ID) {

        // Fetch ToDo item
        ToDo itemToRemove = this.toDoList.getToDo(ID);
        this.toDoList.removeToDo(itemToRemove);
    }

    /* Validate user input method
     *
     */
    private boolean validateUserInput(String title, String message, String dueDate, String category) {

        // Validate easy inputs first
        boolean titleIsValid = title.length() < 50;
        boolean messageIsValid = message.length() < 300;
        boolean categoryIsValid = this.toDoView.listView.getItems().contains(category);

        // Validate date
        boolean dateIsValid = false;
        String[] dateArray = dueDate.split("-");

        if(dateArray.length == 3) {
            int year = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int day = Integer.parseInt(dateArray[2]);
            if (year <= LocalDate.now().getYear() && month < 23 && day < 32) { dateIsValid = true; }
        }

        return (titleIsValid && messageIsValid && categoryIsValid && dateIsValid);

    }

    /* Dialog creation method
     * Shows the dialog to get input from the user required for a new ToDO
     * After user has made his input, controller parses out the data and creates a new ToDo
     * After the new ToDo is created, it wipes the inputs form the dialog pane so we can set up a clean, new dialog
     */
    public void createToDoDialog(MouseEvent e) {

        // Show dialog
        Optional<ButtonType> result = this.toDoView.addToDoDialog.showAndWait();

        // Parse only positive result, ignore CANCEL_CLOSE
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

            // Create new ToDo out of user input
            String title = this.toDoView.toDoDialogPane.titleTextfield.getText();
            String category = this.toDoView.toDoDialogPane.categoryComboBox.getValue();
            String message = this.toDoView.toDoDialogPane.messageTextArea.getText();
            String dueDateString = this.toDoView.toDoDialogPane.datePicker.getValue().toString();

            // Validate user input
            if (this.validateUserInput(title, message, dueDateString, category)) {
                this.createToDo(title, message, LocalDate.parse(dueDateString), category);
            }

            // Clear out dialogPane
            this.toDoView.toDoDialogPane.clearPane();

        }

    }

    /* Method to change center view of GUI
     * We set up a clickListener on the listView and listen on any click
     * On a click, we parse out which item was clicked by it's index
     * Based on which item was clicked, we swap out the center of the main borderPane
     */
    private void changeCenterBar(MouseEvent e) {
        switch (toDoView.listView.getSelectionModel().getSelectedIndex()) {
            case 0:
                ImportantBarView important = new ImportantBarView();
                important.createToDo.setOnMouseClicked(this::createToDoDialog);
                toDoView.borderPane.setCenter(important);
                break;
            case 1:
                PlannedBarView planned = new PlannedBarView();
                planned.createToDo.setOnMouseClicked(this::createToDoDialog);
                toDoView.borderPane.setCenter(planned);
                break;
            case 2:
                DoneBarView done = new DoneBarView();
                done.createToDo.setOnMouseClicked(this::createToDoDialog);
                toDoView.borderPane.setCenter(done);
                break;
            case 3:
                GarbageBarView garbage = new GarbageBarView();
                garbage.createToDo.setOnMouseClicked(this::createToDoDialog);
                toDoView.borderPane.setCenter(garbage);
        }
    }

}
    
    


