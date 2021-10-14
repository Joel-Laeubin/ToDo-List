package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ImportantBarView  extends MainBarView {
	
	public ImportantBarView() {
		super();
		this.icon = new ImageView("/icons/starIcon.png");
		this.label = new Label("Wichtig");
		this.icon.setFitHeight(25);
		this.icon.setFitWidth(25);
		this.header.getChildren().addAll(icon, label);
	
	}
}
