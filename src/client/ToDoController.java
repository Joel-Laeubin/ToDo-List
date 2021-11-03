package client;

import com.sun.tools.javac.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.FocusTimerModel;
import model.ToDo;
import model.ToDoList;
import services.SqliteManager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoController {

    // Fields
    private ToDoView toDoView;
    private ToDo toDo;
    private ToDoList toDoList;

    private ImportantBarView importantBarView;
    private GarbageBarView garbageBarView;
    private PlannedBarView plannedBarView;
    private DoneBarView doneBarView;
    private SearchBarView searchBarView;
    private FocusTimerDialogPane dialog;
    private FocusTimerModel model;
    private SqliteManager sqliteManager;

    // Constructor
    public ToDoController(ToDoView toDoView, ToDo toDo, ToDoList toDoList) {

        this.toDoView = toDoView;
        this.toDo = toDo;
        this.toDoList = toDoList;

        // Set up database handler
        try {
            this.sqliteManager = new SqliteManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set default midPane & add initial event handling for searchbar
        this.plannedBarView = new PlannedBarView(this.toDoList.getToDoListPlanned());
        plannedBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
        this.linkTableViewListeners(plannedBarView.tableView.getItems());
        toDoView.borderPane.setCenter(plannedBarView);

        // Register buttons
        this.plannedBarView.searchButton.setOnMouseClicked(this::searchItemAndGenerateView);
        this.toDoView.listView.setOnMouseClicked(this::changeCenterBar);
        
        // Focus timer button
        this.toDoView.openFocusTimer.setOnMouseClicked(this::createFocusTimer);
        
        // Selected ComboBox
        plannedBarView.comboBox.setOnAction(this::changeCombo); 
        
        // Add focus timer dialog and model 
        this.dialog = new FocusTimerDialogPane();
        this.model = new FocusTimerModel(null);

      
        Timeline Updater = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				toDoView.serie1.getData().clear();
				toDoView.serie2.getData().clear();
				toDoView.serie1.getData().add(new XYChart.Data<String, Number>("Done", toDoList.getToDoListDone().size()));
				toDoView.serie2.getData().add(new XYChart.Data<String, Number>("Undone", toDoList.getToDoListPlanned().size()));
			}
		}));
        
		Updater.setCycleCount(Timeline.INDEFINITE);
		Updater.play();			
		toDoView.bc.getData().addAll(toDoView.serie1, toDoView.serie2);
    	}


    // ------- CRUD-Methods
    /* Create method
     * Parses the inputs of the user required for a new ToDoInstance, creates the instance and stores it.
     * Needs input from ToDoView
     */
    public void createToDo(String title, String message, LocalDate dueDate, String category, ArrayList<String> tags) {
        ToDo toDo = new ToDo(title, message, dueDate, category, tags);
        this.toDoList.addToDo(toDo);
        this.sqliteManager.writeItem(toDo);
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
    public void updateToDo(MouseEvent e) {

        // Check for double click
        if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {

            // Get clicked item
            MainBarView activeMidView = (MainBarView) this.getActiveMidView();
            int index = activeMidView.tableView.getSelectionModel().getSelectedIndex();
            ObservableList<ToDo> items = activeMidView.tableView.getItems();
            ToDo itemToUpdate = items.get(index);

            // Open new dialogPane to make it editable
            this.toDoView.addToDoDialog = new Dialog<ButtonType>();
            this.toDoView.toDoDialogPane = new AddToDoDialogPane(this.toDoView.listView.getItems(), itemToUpdate);
            this.toDoView.addToDoDialog.setDialogPane(this.toDoView.toDoDialogPane);
            Optional<ButtonType> result = this.toDoView.addToDoDialog.showAndWait();

            // Parse only positive result, ignore CANCEL_CLOSE
            if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                // Validate user input
                if (this.validateUserInput()) {

                    // Delete old item from arrayList
                    this.toDoList.getToDoList().remove(itemToUpdate);

                    // Parse out data
                    String title = this.toDoView.toDoDialogPane.titleTextfield.getText();
                    String category = this.toDoView.toDoDialogPane.categoryComboBox.getValue();
                    String message = this.toDoView.toDoDialogPane.messageTextArea.getText();
                    String dueDateString = this.toDoView.toDoDialogPane.datePicker.getValue().toString();
                    String tags = this.toDoView.toDoDialogPane.tagsTextfield.getText();

                    String[] tagArray = tags.replaceAll("\\s", "").split(";");
                    ArrayList<String> tagArrayList = new ArrayList<String>(List.of(tagArray));
                    this.createToDo(title, message, LocalDate.parse(dueDateString), category, tagArrayList);

                }

            }

        }

        // Update lists
        this.updateInstancedSublists();
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
     * ToDo gets deleted from preceding sublist via the .setCategory method.
     */
    private void setToDoAsImportant(MouseEvent e) {
        ToDo toDo = toDoList.getToDo((Button) e.getSource());
        toDo.setCategory("Wichtig");
        this.updateInstancedSublists();
    }

    /* Method to mark ToDo as garbage
     * ToDo gets deleted from preceding sublist via the .setCategory method.
     */
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
            this.linkTableViewListeners(this.importantBarView.tableView.getItems());
        }

        if(this.garbageBarView != null) {
            this.garbageBarView.tableView.getItems().clear();
            this.garbageBarView.tableView.getItems().addAll(this.toDoList.getToDoListGarbage());
            this.linkTableViewListeners(this.garbageBarView.tableView.getItems());
        }

        if(this.plannedBarView != null) {
            this.plannedBarView.tableView.getItems().clear();
            this.plannedBarView.tableView.getItems().addAll(this.toDoList.getToDoListPlanned());
            this.linkTableViewListeners(this.plannedBarView.tableView.getItems());
        }

        if(this.doneBarView != null) {
            this.doneBarView.tableView.getItems().clear();
            this.doneBarView.tableView.getItems().addAll(this.toDoList.getToDoListDone());
            this.linkTableViewListeners(this.doneBarView.tableView.getItems());
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

    /* Method that is linked to the searchButton
     * Does not generate a new view and is only used by searchItemAndGenerateView
     */
    private void searchItem(MouseEvent e) {

        // Fetch input
        MainBarView midView = (MainBarView) this.getActiveMidView();
        String searchString = midView.searchField.getText();

        // Clear pane
        ((MainBarView) this.getActiveMidView()).tableView.getItems().clear();

        // Search items
        ArrayList<ToDo> searchList = this.toDoList.searchItem(searchString);

        // Populate list
        ((MainBarView) this.getActiveMidView()).tableView.getItems().addAll(searchList);

    }

    /* Method that is linked to the searchButton
     * Generates a new view and sets it to the center
     */
    private void searchItemAndGenerateView(MouseEvent e) {

        // Fetch input
        MainBarView midView = (MainBarView) this.getActiveMidView();
        String searchString = midView.searchField.getText();

        // Only go ahead if input is not empty
        if(searchString.length() != 0) {

            // Search items
            ArrayList<ToDo> searchList = this.toDoList.searchItem(searchString);
            ObservableList<ToDo> observableSearchList = FXCollections.observableArrayList(searchList);

            // Generate new searchView
            this.searchBarView = new SearchBarView(observableSearchList);
            this.searchBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
            this.linkTableViewListeners(searchBarView.tableView.getItems());
            this.searchBarView.searchButton.setOnMouseClicked(this::searchItem);

            // Put it on main view
            toDoView.borderPane.setCenter(this.searchBarView);

        }

        // Otherwise just consume the event
        e.consume();
    }

    /* Method that is used to retrieve the active midView
     *
     */
    private Node getActiveMidView() {
        return this.toDoView.borderPane.getCenter();
    }

    /* Validate user input method
     *
     */
    private boolean validateUserInput() {

        // Parse out data
        String title = this.toDoView.toDoDialogPane.titleTextfield.getText();
        String category = this.toDoView.toDoDialogPane.categoryComboBox.getValue();
        String message = this.toDoView.toDoDialogPane.messageTextArea.getText();
        String dueDateString = "";
        try {
            dueDateString = this.toDoView.toDoDialogPane.datePicker.getValue().toString();
            if(dueDateString.equals("")) {
                // Setting default date to today
                this.toDoView.toDoDialogPane.datePicker.setValue(LocalDate.now());
            }
        } catch (NullPointerException e) {
            // Setting default date to today
            this.toDoView.toDoDialogPane.datePicker.setValue(LocalDate.now());
            dueDateString = LocalDate.now().toString();
        }

        String tags = this.toDoView.toDoDialogPane.tagsTextfield.getText();

        // Set default category if none is choosen
        // Note that we need to update the stored variable as it is used for the validity check later
        if(category == null) {
            this.toDoView.toDoDialogPane.categoryComboBox.setValue("Geplant");
            category = this.toDoView.toDoDialogPane.categoryComboBox.getValue();
        }

        // Validate easy inputs first
        boolean titleIsValid = title.length() < 50 && title.length() > 0;
        boolean messageIsValid = message.length() < 300;
        boolean categoryIsValid = this.toDoView.listView.getItems().contains(category);
        boolean tagsAreValid = false;
        String[] tagArray;

        // Validate date
        boolean dateIsValid = false;
        LocalDate paneDate = LocalDate.parse(dueDateString);
        if(paneDate.compareTo(LocalDate.now()) >= 0) { dateIsValid = true; }

        // Validate tags
        // Removes all whitespace and non-visible characters with \\s and splits the string by ;
        try {
             tagArray = tags.replaceAll("\\s", "").split(";");
             tagsAreValid = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tagsAreValid = false;
        }

        // Give graphical feedback
        if (!titleIsValid) {
            this.toDoView.toDoDialogPane.titleTextfield.getStyleClass().add("notOk");
        }
        if (!messageIsValid) {
            this.toDoView.toDoDialogPane.messageTextArea.getStyleClass().add("notOk");
        }
        if (!categoryIsValid) {
            this.toDoView.toDoDialogPane.categoryComboBox.getStyleClass().add("notOk");
        }
        if (!dateIsValid) {
            this.toDoView.toDoDialogPane.datePicker.getStyleClass().add("notOk");
        }
        if (!tagsAreValid) {
            this.toDoView.toDoDialogPane.tagsTextfield.getStyleClass().add("notOk");
        }

        return (titleIsValid && messageIsValid && categoryIsValid && dateIsValid && tagsAreValid);

    }

    /* Method to set event handlers for the tableView Items
     *
     */
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

        // Create & Customize Dialog
        this.toDoView.addToDoDialog = new Dialog<ButtonType>();
        this.toDoView.toDoDialogPane = new AddToDoDialogPane(this.toDoView.listView.getItems());
        this.toDoView.addToDoDialog.setDialogPane(this.toDoView.toDoDialogPane);

        // Set up event filter on OK-button to prevent dialog from closing when user input is not valid
        Button okButton = (Button) this.toDoView.toDoDialogPane.lookupButton(this.toDoView.toDoDialogPane.okButtonType);
        okButton.addEventFilter(ActionEvent.ACTION,
                event -> { if(!validateUserInput()) { event.consume(); }});

        // Clear graphical validation
        this.toDoView.toDoDialogPane.titleTextfield.getStyleClass().remove("notOk");
        this.toDoView.toDoDialogPane.messageTextArea.getStyleClass().remove("notOk");
        this.toDoView.toDoDialogPane.categoryComboBox.getStyleClass().remove("notOk");
        this.toDoView.toDoDialogPane.datePicker.getStyleClass().remove("notOk");
        this.toDoView.toDoDialogPane.tagsTextfield.getStyleClass().remove("notOk");

        // Show dialog
        Optional<ButtonType> result = this.toDoView.addToDoDialog.showAndWait();

        // Parse only positive result, ignore CANCEL_CLOSE
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

            // Validate user input
            if (this.validateUserInput()) {

                // Parse out data
                String title = this.toDoView.toDoDialogPane.titleTextfield.getText();
                String category = this.toDoView.toDoDialogPane.categoryComboBox.getValue();
                String message = this.toDoView.toDoDialogPane.messageTextArea.getText();
                String dueDateString = this.toDoView.toDoDialogPane.datePicker.getValue().toString();
                String tags = this.toDoView.toDoDialogPane.tagsTextfield.getText();

                String[] tagArray = tags.replaceAll("\\s", "").split(";");
                ArrayList<String> tagArrayList = new ArrayList<String>(List.of(tagArray));
                this.createToDo(title, message, LocalDate.parse(dueDateString), category, tagArrayList);

            }

        }

        // Clear out dialogPane
        this.toDoView.toDoDialogPane.clearPane();

        // Add editing functionality
        MainBarView midView = (MainBarView) this.getActiveMidView();
        midView.tableView.setOnMouseClicked(this::updateToDo);

        // Refresh views
        this.updateInstancedSublists();

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
                importantBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
                linkTableViewListeners(importantBarView.tableView.getItems());
                importantBarView.searchButton.setOnMouseClicked(this::searchItemAndGenerateView);
                importantBarView.comboBox.setOnAction(this::changeCombo);
                importantBarView.tableView.setOnMouseClicked(this::updateToDo);

                // Put it on main view
                toDoView.borderPane.setCenter(importantBarView);
            }
            case 1 -> {
                plannedBarView = new PlannedBarView(this.toDoList.getToDoListPlanned());
                plannedBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
                plannedBarView.searchButton.setOnMouseClicked(this::searchItemAndGenerateView);
                plannedBarView.comboBox.setOnAction(this::changeCombo);
                plannedBarView.tableView.setOnMouseClicked(this::updateToDo);
                linkTableViewListeners(plannedBarView.tableView.getItems());
                toDoView.borderPane.setCenter(plannedBarView);
            }
            case 2 -> {
                doneBarView = new DoneBarView(this.toDoList.getToDoListDone());
                doneBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
                doneBarView.searchButton.setOnMouseClicked(this::searchItemAndGenerateView);
                doneBarView.comboBox.setOnAction(this::changeCombo);
                doneBarView.tableView.setOnMouseClicked(this::updateToDo);
                linkTableViewListeners(doneBarView.tableView.getItems());
                toDoView.borderPane.setCenter(doneBarView);
            }
            case 3 -> {
                garbageBarView = new GarbageBarView(this.toDoList.getToDoListGarbage());
                garbageBarView.createToDo.setOnMouseClicked(this::createToDoDialog);
                garbageBarView.searchButton.setOnMouseClicked(this::searchItemAndGenerateView);
                garbageBarView.comboBox.setOnAction(this::changeCombo);
                garbageBarView.tableView.setOnMouseClicked(this::updateToDo);
                linkTableViewListeners(garbageBarView.tableView.getItems());
                toDoView.borderPane.setCenter(garbageBarView);
            }
        }
    }
    // Open a new focus timer window
    public void createFocusTimer(MouseEvent e) {

    	// show dialog
    	this.toDoView.focusDialog.showAndWait();
    }

    /*
     * Depending on which date filter (ComboBox) the user choosed,
	 * the ToDo task-view will change.
     */
    
    private void changeCombo(ActionEvent event) {
    	MainBarView main = (MainBarView) getActiveMidView();
    	switch (main.comboBox.getSelectionModel().getSelectedIndex()) {
    	case 0: {
    		ObservableList<ToDo> observableListAll = FXCollections.observableArrayList(this.toDoList.getToDoList());
    		main.tableView.getItems().clear();
    		main.tableView.getItems().addAll(observableListAll);
    		break;
    	}
    	case 1: {
    		ArrayList<ToDo> arrayListToday = this.toDoList.searchLocalToday();
    		ObservableList<ToDo> observableListToday = FXCollections.observableArrayList(arrayListToday);
    		main.tableView.getItems().clear();
    		main.tableView.getItems().addAll(observableListToday);
    		break;
    	}
    	case 2: {
    		ArrayList<ToDo> arrayListWeek = this.toDoList.searchLocalWeek();
    		ObservableList<ToDo> observableListWeek = FXCollections.observableArrayList(arrayListWeek);
    		main.tableView.getItems().clear();
    		main.tableView.getItems().addAll(observableListWeek);
    		break;
    	}
    	case 3: {
    		ArrayList<ToDo> arrayListMonth = this.toDoList.searchLocalMonth();
    		ObservableList<ToDo> observableListMonth = FXCollections.observableArrayList(arrayListMonth);
    		main.tableView.getItems().clear();
    		main.tableView.getItems().addAll(observableListMonth);
    	}
    	}
    }
    
    /*
   	 * New Constructor for FocusTimer
   	 * EventHandling for the playButton, replayButton and stopButton.
   	 * - If the user clicks on the playButton, the timer will start counting from 25 minutes backwards. 
   	 * - If the user clicks on the replayButton, the timer will go back to 25 minutes and start counting backwards.
   	 * - If the user clicks on the stopButton, the timer will stop immediately.
   	 */
    
    public ToDoController(FocusTimerModel model, FocusTimerDialogPane dialog) {
    	
    	this.model = model;
    	this.dialog = dialog;
    	
    	 // EventHandling for focus timer
         this.toDoView.focusTimerDialog.playButton.setOnMouseClicked(this::playTimer);
         this.toDoView.focusTimerDialog.stopButton.setOnMouseClicked(this::stopTimer);
         this.toDoView.focusTimerDialog.replayButton.setOnMouseClicked(this::replayTimer);

    }
    
    public void playTimer(MouseEvent event) {
        	 if (!model.isRunning()) {
                model.start();
                model.setRunning(true);
        	 }
    }
        	 
    public void replayTimer(MouseEvent event) {
    			model.restart();
    }
    
    public void stopTimer(MouseEvent event) {
             if (model.isRunning()) {
                 model.pause();
                 model.setRunning(false);
    }
    
    }   
	
 
}

		
	

	

	
		
	

