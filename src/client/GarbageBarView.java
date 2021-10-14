package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GarbageBarView  extends MainBarView {
	
	public GarbageBarView() {
		
		/*
		 * Inherits defined elements from super class MainBarView,
		 * which are needed to change the SideBar in the GUI
		 */
		super();
		
		// Individual icons and labels for this view
		this.icon = new ImageView("/icons/garbageIcon.png");
		this.label = new Label("Papierkorb");
		this.icon.setFitHeight(25);
		this.icon.setFitWidth(25);
		this.header.getChildren().addAll(icon, label);
	}
	

}
