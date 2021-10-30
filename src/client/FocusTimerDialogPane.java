package client;

import java.util.Timer;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	protected HBox hBoxButtons;
	
	protected VBox vBoxPlay;
	protected VBox vBoxStop;
	protected VBox vBoxReplay;
	
	protected Timer timer;
	protected Timeline timeline;
	
	protected ButtonType closeButtonType;
	
	public FocusTimerDialogPane() {
		
		this.counter = new Text("25:00");
		
		this.playIcon = new ImageView("/icons/startIcon.png");
		this.stopIcon = new ImageView("/icons/stopIcon.png");
		this.replayIcon = new ImageView("/icons/restartIcon.png");
		
		this.playButton = new Button();
		this.playButton.setGraphic(playIcon);
		
		this.stopButton = new Button();
		this.stopButton.setGraphic(stopIcon);
		
		this.replayButton = new Button();
		this.replayButton.setGraphic(replayIcon);
		
		this.timerBorderPane = new BorderPane();
		
		this.hBoxButtons = new HBox();
		this.vBoxText = new VBox();
		
		this.vBoxPlay = new VBox();
		this.vBoxStop = new VBox();
		this.vBoxReplay = new VBox();
		
		this.vBoxPlay.getChildren().add(playButton);
		this.vBoxStop.getChildren().add(stopButton);
		this.vBoxReplay.getChildren().add(replayButton);
		
		this.vBoxText.getChildren().add(counter);
		
		this.hBoxButtons.getChildren().addAll(vBoxPlay, vBoxStop, vBoxReplay);
		
		this.timerBorderPane.setCenter(vBoxText);
		this.timerBorderPane.setBottom(hBoxButtons);
		
		// Add ButtonType
		this.closeButtonType = new ButtonType("Beenden", ButtonBar.ButtonData.CANCEL_CLOSE);
		this.getButtonTypes().add(closeButtonType);
		
		this.timeline = new Timeline();
		
		this.setContent(timerBorderPane);
		
	}
	
	public Text getCounter() {
		return counter;
	}
	
	public Timeline getTimeline() {
		return timeline;
	}
	
	
	
	
	
}