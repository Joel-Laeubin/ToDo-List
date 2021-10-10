package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DoneBarView extends MainBarView {
	
	public DoneBarView() {
		super();
		this.icon = new ImageView("/icons/doneIcon2.png");
		this.label = new Label("Erledigt");
		this.icon.setFitHeight(25);
		this.icon.setFitWidth(25);
		this.header.getChildren().addAll(icon, label);
	}


	

	
}
