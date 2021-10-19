package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ToDo;

/*
 * This abstract class is the super class
 * of the GUI-side-bar, which changes
 * based on the clicked ListView item
 */
public abstract class MainBarView extends VBox {
	
	// control elements for this container
	protected ImageView icon;
	protected Label label;
	protected ImageView lupe;
	protected TextField searchField;
	protected Button createToDo;
	protected TableView<ToDo> tableView;
	protected TableColumn<ToDo, String> checkBox;
	protected TableColumn<ToDo, String> task;
	protected TableColumn<ToDo, String> dueDate;
	protected TableColumn<ToDo, String> important;
	protected TableColumn<ToDo, String> garbage;
	protected ScrollPane scrollPane;
	protected HBox header;
	
	// Constructor
	public MainBarView() {
		
		/*
		 * HBox for the icon and label in the
		 * GUI-SideBar (items will be set in
		 * the subclass)
		 */
		this.header = new HBox();
		this.getChildren().add(header);
		
		// Lupe Icon for the searchField		
		this.lupe = new ImageView("/icons/lupe.png");
		this.lupe.setFitHeight(25);
		this.lupe.setFitWidth(40);
		this.getChildren().add(lupe);
		
		this.searchField = new TextField();
		this.getChildren().add(searchField);
		this.searchField.setMaxWidth(200);
		
		// Button for a new task
		this.createToDo = new Button("+");
		this.getChildren().add(createToDo);
		
		/*
         * Creates a TableView with Columns
         * and includes data from ObservableArrayList.
         * The setCellValueFactory method specifies a cell factory for each column. 
         */
    		this.tableView = new TableView<>();
    		this.tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    		this.tableView.setEditable(true);
    	    
    		this.checkBox = new TableColumn<>("Erledigt");
    		this.checkBox.setCellValueFactory(new PropertyValueFactory<ToDo, String>("doneButton"));
    	    
    		this.task = new TableColumn<>("Aufgabe");
    		this.task.setCellValueFactory(new PropertyValueFactory<ToDo, String>("title"));
    		
    		this.dueDate = new TableColumn<>("Termin");
    		this.dueDate.setCellValueFactory(new PropertyValueFactory<ToDo, String>("dueDate"));

    		this.important = new TableColumn<>("Wichtig");
    		this.important.setCellValueFactory(new PropertyValueFactory<ToDo, String>("importantButton"));
    	    
       		this.garbage = new TableColumn<>("Papierkorb");
    		this.garbage.setCellValueFactory(new PropertyValueFactory<ToDo, String>("garbageButton"));
    	    
    		
    	    // Adds Columns to the TableView
    		this.tableView.getColumns().addAll(this.checkBox, this.task, this.dueDate, this.important, this.garbage);
    	    
    		this.getChildren().addAll(tableView);
		
		/*
		 * ScrollPane helps to see
		 * all tasks, if there are more
		 * than the GUI shows
		 */
		this.scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setPrefSize(500,  200);
		this.getChildren().add(scrollPane);
		
		
		
	}


}
	

