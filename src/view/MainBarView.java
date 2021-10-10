package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public abstract class MainBarView extends VBox {
	
	// control elements for this container
	protected Image icon;
	protected Label label;
	protected ImageView lupe;
	protected TextField searchField;
	protected Button createButton;
	protected TableView tableView;
	protected ScrollPane scrollPane;
	
	
	public ImageView getLupe() {
		this.lupe = new ImageView("/icons/lupe.png");
		this.getChildren().add(lupe);
		return this.lupe;
	}
	public TextField getSearchField() {
		this.searchField = new TextField();
		this.getChildren().add(searchField);
		return this.searchField;
	}
	public Button getCreateButton() {
		this.createButton = new Button("+");
		this.getChildren().add(createButton);
		return createButton;
	}
	public TableView getTableView() {
		TableView tableView = new TableView();
		tableView.setEditable(true);
		TableColumn checkbox = new TableColumn("Erledigt");
		TableColumn task = new TableColumn("Aufgabe");
		TableColumn deadline = new TableColumn("Termin");
		TableColumn important = new TableColumn("Wichtig");
		tableView.getColumns().addAll(checkbox, task, deadline, important);
		this.getChildren().addAll(tableView);
		return tableView;
	}
	public ScrollPane getScrollPane() {
		ScrollPane scrollpane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setPrefSize(500,  200);
		this.getChildren().add(scrollPane);
		return scrollPane;
	}
	
	public abstract void setIcon();
	
	
	public abstract void setLabel();
	
}
	

