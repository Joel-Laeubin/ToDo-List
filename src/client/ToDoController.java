package client;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
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

    private ImportantBarView importantBarView;
    private GarbageBarView garbageBarView;
    private PlannedBarView plannedBarView;
    private DoneBarView doneBarView;


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
    
    /* Method to set a ToDo on done ("Erledigt") whenever the button is clicked.
     * Fetches out the corresponding toDo from the button clicked
     * Deletes all other categories from the toDo, since a toDo can only be 'done' when it's done
     */
    public void setToDoOnDone(MouseEvent e) {
        ToDo toDo = toDoList.getToDo((Button) e.getSource());
        toDo.setCategory("Erledigt");
        toDo.setDone(true);
        this.updateInstancedSublists();
        
    }

    /* Method to mark ToDo as important
     * ToDo gets deleted from preceding sublist via the
     */
    private void setToDoAsImportant(MouseEvent e) {
        ToDo toDo = toDoList.getToDo((Button) e.getSource());
        toDo.setCategory("Wichtig");
        this.updateInstancedSublists();
    }

    private void setToDoAsGarbage(MouseEvent e) {
        ToDo toDo = toDoList.getToDo((Button) e.getSource());
        toDo.setCategory("Papierkorb");
        this.updateInstancedSublists();
    }

    /* Method to update the sublists inside the instances of each view
     * Checks if each view exists and updates the passed sublist if so
     */
    private void updateInstancedSublists() {

        // Update current sublists
        this.toDoList.updateSublists();

        // Update sublists in each view
        if(this.importantBarView != null) {
            this.importantBarView.tableView.getItems().clear();
            this.importantBarView.tableView.getItems().addAll(this.toDoList.getToDoListImportant());
        }

        if(this.garbageBarView != null) {
            this.garbageBarView.tableView.getItems().clear();
            this.garbageBarView.tableView.getItems().addAll(this.toDoList.getToDoListGarbage());
        }

        if(this.plannedBarView != null) {
            this.plannedBarView.tableView.getItems().clear();
            this.plannedBarView.tableView.getItems().addAll(this.toDoList.getToDoListPlanned());
        }

        if(this.doneBarView != null) {
            this.doneBarView.tableView.getItems().clear();
            this.doneBarView.tableView.getItems().addAll(this.toDoList.getToDoListDone());
        }
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

    private void linkTableViewListeners(ObservableList<ToDo> listItems) {
        for(ToDo toDo : listItems) {
            toDo.getDoneButton().setOnMouseClicked(this::setToDoOnDone);
            toDo.getImportantButton().setOnMouseClicked(this::setToDoAsImportant);
            toDo.getGarbageButton().setOnMouseClicked(this::setToDoAsGarbage);
        }
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
     * ----------------------------------- Swapping out centerView
     * We set up a clickListener on the (main) listView and listen on any click
     * On a click, we parse out which item was clicked by its index
     * Based on which item was clicked, we swap out the center of the main borderPane with the corresponding view
     * ----------------------------------- Adding listeners to the rows of the tableview
     * Since we have buttons inside the tableView, they need to be addressed by the controller as well.
     * However, the concept of a javaFX-tableView intends to represent instances of a model inside each row.
     * On the other hand, the MVC pattern demands a strict separation of model (data), and view.
     * This leads to a dilemma, where each solution violates one of the concepts. Placing the button inside the model
     * violates the MVC concept, placing the button inside the tableView via a workaround violates the intends of javaFX.
     * Anyhow - we see less dissonance in adding a button to a model, since this can be perceived as a "trait" of the model.
     */
    private void changeCenterBar(MouseEvent e) {
        switch (toDoView.listView.getSelectionModel().getSelectedIndex()) {
            case 0 -> {
                // Create new instance of the view, populated with up-to-date dataset
                this.importantBarView = new ImportantBarView(this.toDoList.getToDoListImportant());

                // Add listeners
                this.importantBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
                this.linkTableViewListeners(importantBarView.tableView.getItems());

                // Put it on main view
                toDoView.borderPane.setCenter(importantBarView);
            }
            case 1 -> {
                this.plannedBarView = new PlannedBarView(this.toDoList.getToDoListPlanned());
                plannedBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
                this.linkTableViewListeners(plannedBarView.tableView.getItems());
                toDoView.borderPane.setCenter(plannedBarView);
            }
            case 2 -> {
                this.doneBarView = new DoneBarView(this.toDoList.getToDoListDone());
                doneBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
                this.linkTableViewListeners(doneBarView.tableView.getItems());
                toDoView.borderPane.setCenter(doneBarView);
            }
            case 3 -> {
                this.garbageBarView = new GarbageBarView(this.toDoList.getToDoListGarbage());
                garbageBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
                this.linkTableViewListeners(garbageBarView.tableView.getItems());
                toDoView.borderPane.setCenter(garbageBarView);
            }
        }
        
        
    }

}
    
