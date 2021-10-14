package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DoneBarView extends MainBarView {
	
	public DoneBarView() {
		
		/*
		 * Inherits defined elements from super class MainBarView,
		 * which are needed to change the SideBar in the GUI
		 */
		super();
		
		// Individual icons and labels for this view
		this.icon = new ImageView("/icons/doneIcon2.png");
		this.label = new Label("Erledigt");
		this.icon.setFitHeight(25);
		this.icon.setFitWidth(25);
		this.header.getChildren().addAll(icon, label);
	}


	

	
}
