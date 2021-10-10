package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlannedBarView  extends MainBarView {
	
	public PlannedBarView() {
		super();
		this.icon = new ImageView("/icons/plannedIcon.png");
		this.label = new Label("Geplant");
		this.icon.setFitHeight(25);
		this.icon.setFitWidth(25);
		this.header.getChildren().addAll(icon, label);
	}
	

}
