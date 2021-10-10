package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class ImportantBarView  extends MainBarView {

	public void setIcon() {
		Image importantIcon = new Image("/icons/starIcon.png");
	}

	
	public void setLabel() {
		Label importantLabel = new Label("Wichtig");
		
	}

}
