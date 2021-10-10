package client;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class GarbageBarView  extends MainBarView {

	public void setIcon() {
		Image garbageIcon = new Image("/icons/garbageIcon.png");
	}

	
	public void setLabel() {
		Label garbageLabel = new Label("Papierkorb");
		
	}



	

}
