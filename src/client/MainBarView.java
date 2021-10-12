package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	
	
	public MainBarView() {
		
		this.header = new HBox();
		this.getChildren().add(header);
		
		this.lupe = new ImageView("/icons/lupe.png");
		
		this.searchField = new TextField();
		this.getChildren().add(searchField);
		
		this.createToDo = new Button("+");
		this.getChildren().add(createToDo);
		
		this.tableView = new TableView();
		tableView.setEditable(true);
		TableColumn checkbox = new TableColumn("Erledigt");
		TableColumn task = new TableColumn("Aufgabe");
		TableColumn dueDate = new TableColumn("Termin");
		TableColumn important = new TableColumn("Wichtig");
		tableView.getColumns().addAll(checkbox, task, dueDate, important);
		this.getChildren().addAll(tableView);
		
		this.scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setPrefSize(500,  200);
		this.getChildren().add(scrollPane);
		
	}
	
	public ImageView getLupe() {
		return this.lupe;
	}
	public TextField getSearchField() {
		return this.searchField;
	}
	public Button getCreateToDo() {
		return createToDo;
	}
	public TableView getTableView() {
		return tableView;
	}
	public ScrollPane getScrollPane() {
		return scrollPane;
	}
	

	
}
	

