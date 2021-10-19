package client;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.ToDo;


public class GarbageBarView  extends MainBarView {
	
	public GarbageBarView(ObservableList<ToDo> toDoListGarbage) {
		
		/*
		 * Inherits defined elements from super class MainBarView,
		 * which are needed to change the SideBar in the GUI
		 */
		super();
		
		// Individual icons and labels for this view
		this.icon = new ImageView("/icons/garbageIcon.png");
		this.label = new Label("Papierkorb");
		this.icon.setFitHeight(50);
		this.icon.setFitWidth(50);
		this.header.getChildren().addAll(icon, label);
		
		// Gets items of ObservableArrayList from method getToDoListImportant
		this.tableView.getItems().addAll(toDoListGarbage);
	}
	

}
