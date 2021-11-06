package client;

import java.io.File;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class HowToDialogPane extends DialogPane {
	
	private Button playButton;
	private Button stopButton;
	private Button replayButton;
	
	private ImageView playIcon;
	private ImageView stopIcon;
	private ImageView replayIcon;

	private HBox hBoxButtons; 
	
	private VBox vBoxPlay;
	private VBox vBoxStop;
	private VBox vBoxReplay;
	
	private Media media;
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;
	
	protected BorderPane howToBorderPane;
	
	protected ButtonType closeButtonType;

	public HowToDialogPane() {
	
	// Create Media
	String path = new File("src/icons/howTo.mp4").getAbsolutePath();
	this.media = new Media(new File(path).toURI().toString());
	this.mediaPlayer = new MediaPlayer(media);
	this.mediaView = new MediaView();
	this.mediaView.setMediaPlayer(mediaPlayer);
	
	// Vergrössert Video
	DoubleProperty width = mediaView.fitWidthProperty();
	DoubleProperty height = mediaView.fitWidthProperty();
	width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
	height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
	
	// BorderPane
	this.howToBorderPane = new BorderPane();
	this.howToBorderPane.setCenter(mediaView);
	this.howToBorderPane.setMinWidth(1250);
	this.howToBorderPane.setMinHeight(900);

	// Icon for PlayButton
	this.playIcon = new ImageView("/icons/startIcon.png");
	this.playIcon.setFitHeight(40);
	this.playIcon.setFitWidth(40);
	
	// Icon for StopButton
	this.stopIcon = new ImageView("/icons/stopIcon.png");
	this.stopIcon.setFitHeight(40);
	this.stopIcon.setFitWidth(40);
	
	// Icon for ReplayButton
	this.replayIcon = new ImageView("/icons/restartIcon.png");
	this.replayIcon.setFitHeight(40);
	this.replayIcon.setFitWidth(40);

	// PlayButton
	this.playButton = new Button();
	this.playButton.setGraphic(playIcon);
	this.playButton.setAlignment(Pos.CENTER);
	this.playButton.setPrefSize(40, 40);
	
	// StopButton
	this.stopButton = new Button();
	this.stopButton.setGraphic(stopIcon);
	this.stopButton.setAlignment(Pos.CENTER);
	this.stopButton.setPrefSize(40, 40);
	
	//ReplayButton
	this.replayButton = new Button();
	this.replayButton.setGraphic(replayIcon);
	this.replayButton.setAlignment(Pos.CENTER);
	this.replayButton.setPrefSize(40, 40);
	
	
	this.vBoxPlay = new VBox();
	this.vBoxPlay.getChildren().add(playButton);
	this.vBoxPlay.setSpacing(10);
	
	this.vBoxStop = new VBox();
	this.vBoxStop.getChildren().add(stopButton);
	this.vBoxStop.setSpacing(10);
	
	this.vBoxReplay = new VBox();
	this.vBoxReplay.getChildren().add(replayButton);
	this.vBoxReplay.setSpacing(10);
	
	this.hBoxButtons = new HBox();
	this.hBoxButtons.getChildren().addAll(vBoxPlay, vBoxStop, vBoxReplay);
	this.hBoxButtons.setSpacing(10);
	this.hBoxButtons.setAlignment(Pos.CENTER);
	this.hBoxButtons.setPadding(new Insets(50.0, 0.0, 0.0, 20.0));
	
	this.howToBorderPane.setBottom(hBoxButtons);
	
	// Add ButtonType
	this.closeButtonType = new ButtonType("Beenden", ButtonBar.ButtonData.CANCEL_CLOSE);
	this.getButtonTypes().add(closeButtonType);
	
	
	this.setContent(howToBorderPane);
	this.setPrefSize(500, 200);
	
	//Add css-styling
	this.getStylesheets().add(getClass().getResource("FocusAndHowToDialogPaneStyleSheet.css").toExternalForm());
	this.howToBorderPane.getStyleClass().add("root");
	this.playButton.getStyleClass().add("button");
	this.stopButton.getStyleClass().add("button");
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public ButtonType getCloseButtonType() {
		return closeButtonType;
	}
	
	public Button getPlayButton() {
		return playButton;
	}
	
	public Button getStopButton() {
		return stopButton;
	}
	
	public Button getReplayButton() {
		return replayButton;
	}
	
	public MediaView getMediaView() {
		return mediaView;
	}
	
	}
	