package client;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.ToDo;

public class ImportantBarView  extends MainBarView {
	
	public ImportantBarView(ObservableList<ToDo> toDoListImportant) {
		
		/*
		 * Inherits defined elements from super class MainBarView,
		 * which are needed to change the SideBar in the GUI
		 */
		super();
		
		// Individual icons and labels for this view
		this.icon = new ImageView("/icons/starIcon.png");
		this.label = new Label("Wichtig");
		this.icon.setFitHeight(25);
		this.icon.setFitWidth(25);
		this.header.getChildren().addAll(icon, label);

		// Gets items of ObservableArrayList from method getToDoListImportant
		this.tableView.getItems().addAll(toDoListImportant);
}
		
	}

