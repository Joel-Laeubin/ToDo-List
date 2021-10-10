package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GarbageBarView  extends MainBarView {
	
	public GarbageBarView() {
		super();
		this.icon = new ImageView("/icons/garbageIcon.png");
		this.label = new Label("Papierkorb");
		this.icon.setFitHeight(25);
		this.icon.setFitWidth(25);
		this.header.getChildren().addAll(icon, label);
	}
	

}
