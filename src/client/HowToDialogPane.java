package client;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HowToDialogPane extends DialogPane {
	
	protected Button playButton;
	protected Button stopButton;
	protected Button replayButton;
	
	protected ImageView playIcon;
	protected ImageView stopIcon;
	protected ImageView replayIcon;
	
	protected VBox vBoxVideo;
	protected HBox hBoxButtons;
	
	protected VBox vBoxPlay;
	protected VBox vBoxStop;
	protected VBox vBoxReplay;
	
	protected BorderPane howToBorderPane;
	
	protected ButtonType closeButtonType;

	public HowToDialogPane() {

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
	
	this.howToBorderPane = new BorderPane();
	
	this.hBoxButtons = new HBox();
	this.hBoxButtons.setAlignment(Pos.CENTER);
	this.hBoxButtons.setSpacing(20);
	this.vBoxVideo = new VBox();
	
	this.vBoxPlay = new VBox();
	this.vBoxStop = new VBox();
	this.vBoxReplay = new VBox();

	this.vBoxPlay.getChildren().add(playButton);
	this.vBoxStop.getChildren().add(stopButton);
	this.vBoxReplay.getChildren().add(replayButton);
	
	// this.vBoxVideo.getChildren().add(counterLabel);

	
	this.hBoxButtons.getChildren().addAll(vBoxPlay, vBoxStop, vBoxReplay);
	
	//this.timerBorderPane.setCenter(vBoxText);
	this.howToBorderPane.setBottom(hBoxButtons);
	
	// Add ButtonType
	this.closeButtonType = new ButtonType("Beenden", ButtonBar.ButtonData.CANCEL_CLOSE);
	this.getButtonTypes().add(closeButtonType);
	
	this.setContent(howToBorderPane);
	
	//Add css-styling
	this.getStylesheets().add(getClass().getResource("FocusAndHowToDialogPaneStyleSheet.css").toExternalForm());
	this.howToBorderPane.getStyleClass().add("root");
	this.playButton.getStyleClass().add("button");
	this.stopButton.getStyleClass().add("button");
	this.replayButton.getStyleClass().add("button");
	

	}	
	
	}
	