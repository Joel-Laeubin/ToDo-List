package client;

import java.util.Timer;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FocusTimerDialogPane extends DialogPane {
	
	protected ToDoController controller;
	
	protected Button playButton;
	protected Button stopButton;
	protected Button replayButton;
	
	protected ImageView playIcon;
	protected ImageView stopIcon;
	protected ImageView replayIcon;
	
	protected Text counter;
	
	protected BorderPane timerBorderPane;
	protected VBox vBoxText;
	protected VBox vBoxButtons;
	
	protected Timer time;
	protected Timeline timeline;
	
	ButtonType closeButtonType;
	
	public FocusTimerDialogPane() {
		
		this.counter = new Text();
		this.counter.setText("25:00");
		
		this.playIcon = new ImageView("/icons/startIcon.png");
		this.stopIcon = new ImageView("/icons/stopIcon.png");
		this.replayIcon = new ImageView("/icons/restartIcon.png");
			
		this.playButton = new Button();
		this.playButton.setGraphic(playIcon);
				
		this.stopButton = new Button();
		this.stopButton.setGraphic(stopButton);
		
		this.replayButton = new Button();
		this.replayButton.setGraphic(replayButton);
		
		// this.timeline = new Timeline();
		
		this.timerBorderPane = new BorderPane();
		this.vBoxText = new VBox();
		this.vBoxButtons = new VBox();
		
		this.vBoxText.getChildren().add(counter);
		this.vBoxButtons.getChildren().addAll(playButton, stopButton, replayButton);
		
		this.timerBorderPane.setCenter(vBoxText);
		this.timerBorderPane.setBottom(vBoxButtons);
		
		this.setContent(timerBorderPane);
		
		// Add buttonTypes
		closeButtonType = new ButtonType("Beenden", ButtonBar.ButtonData.CANCEL_CLOSE);
		this.getButtonTypes().add(closeButtonType);
			
		
		this.timeline = new Timeline();
		
	}

public Text getCounter() {
	return counter;
}

public Timeline getTimeline() {
	return timeline;
}

}
