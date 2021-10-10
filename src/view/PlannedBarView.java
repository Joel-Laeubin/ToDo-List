package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class PlannedBarView  extends MainBarView {
	
	public void setIcon() {
		Image plannedIcon = new Image("/icons/plannedIcon.png");
	}

	
	public void setLabel() {
		Label plannedLabel = new Label("Geplant");
		
	}
	

}
