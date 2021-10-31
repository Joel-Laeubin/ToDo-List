package client;

import java.util.Timer;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FocusTimerDialogPane extends DialogPane {
	
	protected ToDoController controller;
	
	protected Button playButton;
	protected Button stopButton;
	protected Button replayButton;
	
	protected ImageView playIcon;
	protected ImageView stopIcon;
	protected ImageView replayIcon;
	
	protected Label counter;
	
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
		
		this.counter = new Label("25:00");
		this.counter.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
		this.counter.setAlignment(Pos.CENTER);
		
		this.playIcon = new ImageView("/icons/startIcon.png");
		this.playIcon.setFitHeight(50);
		this.playIcon.setFitWidth(50);
		this.playButton = new Button();
		this.playButton.setGraphic(playIcon);
		
		this.stopIcon = new ImageView("/icons/stopIcon.png");
		this.stopIcon.setFitHeight(50);
		this.stopIcon.setFitWidth(50);
		this.stopButton = new Button();
		this.stopButton.setGraphic(stopIcon);
		
		this.replayIcon = new ImageView("/icons/restartIcon.png");
		this.replayIcon.setFitHeight(50);
		this.replayIcon.setFitWidth(50);
		this.replayButton = new Button();
		this.replayButton.setGraphic(replayIcon);
		
		this.timerBorderPane = new BorderPane();
		
		this.hBoxButtons = new HBox();
		this.hBoxButtons.setAlignment(Pos.CENTER);
		this.hBoxButtons.setSpacing(20);
		this.vBoxText = new VBox();
		
		this.vBoxPlay = new VBox();
		this.vBoxStop = new VBox();
		this.vBoxReplay = new VBox();
	
		this.vBoxPlay.getChildren().add(playButton);
		this.vBoxStop.getChildren().add(stopButton);
		this.vBoxReplay.getChildren().add(replayButton);
		
		this.vBoxText.getChildren().add(counter);
		this.vBoxText.setAlignment(Pos.CENTER);
		this.vBoxText.setSpacing(100);
		
		this.hBoxButtons.getChildren().addAll(vBoxPlay, vBoxStop, vBoxReplay);
		
		this.timerBorderPane.setCenter(vBoxText);
		this.timerBorderPane.setBottom(hBoxButtons);
		
		// Add ButtonType
		this.closeButtonType = new ButtonType("Beenden", ButtonBar.ButtonData.CANCEL_CLOSE);
		this.getButtonTypes().add(closeButtonType);
		
		this.timeline = new Timeline();
		this.timer = new Timer();
		
		this.setContent(timerBorderPane);
		
	}
	
	public Label getCounter() {
		return counter;
	}
	
	public Timeline getTimeline() {
		return timeline;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	
	
	
	
}