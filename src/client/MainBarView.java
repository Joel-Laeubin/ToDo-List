package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	protected TableView tableView;
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
		 * TableView shows the tasks
		 */
		this.tableView = new TableView();
		tableView.setEditable(true);
		TableColumn checkbox = new TableColumn("Erledigt");
		TableColumn task = new TableColumn("Aufgabe");
		TableColumn dueDate = new TableColumn("Termin");
		TableColumn important = new TableColumn("Wichtig");
		tableView.getColumns().addAll(checkbox, task, dueDate, important);
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
	

