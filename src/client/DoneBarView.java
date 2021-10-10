package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class DoneBarView extends MainBarView {

	public void setIcon() {
		Image doneIcon = new Image("/icons/doneIcon2.png");
	}

	
	public void setLabel() {
		Label doneLabel = new Label("Erledigt");
		
	}

	

	
}
