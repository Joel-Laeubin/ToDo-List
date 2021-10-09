package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public abstract class MainBarView extends VBox {
	
	// control elements for this container
	protected Image icon;
	protected Label label;
	protected final Image LUPE;
	protected TextField searchField;
	protected Button createButton;
	protected TableView tableView;
	protected ScrollPane scrollPane;

	// setter	
	public void setLupe() {
		this.LUPE = new Image("/icons/lupe.png");
	}
	
	public void setSearchField() {
		this.searchField = new TextField();
	}
	
	public void setCreateButton() {
		this.createButton = new Button("+");
	}
	
	public void setTableView() {
		this.tableView = new TableView();
		this.getChildren().add(tableView);
	}
	
	public void setScrollPane() {
		this.scrollPane = new ScrollPane();
		this.getChildren().add(scrollPane);
	}
	
		
	/*
	 * Abstract methods (have to be formulated
	 * in the subclass
	 */
	
	public abstract Image setIcon();
	
	
	public abstract Label setLabel();
	

	{
	this.getChildren().add(this.LUPE);
	
	
	
	
	}

}


	
	

