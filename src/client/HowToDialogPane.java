package client;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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
	
	private VBox vBoxVideo;
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
	String path = new File("src/icons/focusTimer.mp4").getAbsolutePath();
	this.media = new Media(new File(path).toURI().toString());
	this.mediaPlayer = new MediaPlayer(media);
	this.mediaView = new MediaView();
	this.mediaView.setMediaPlayer(mediaPlayer);

	
	// Makes Video bigger
	DoubleProperty width = mediaView.fitWidthProperty();
	DoubleProperty height = mediaView.fitHeightProperty();
	width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
	height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
	
	// BorderPane
	this.howToBorderPane = new BorderPane();
	
	// Add ButtonType
	this.closeButtonType = new ButtonType("Beenden", ButtonBar.ButtonData.CANCEL_CLOSE);
	this.getButtonTypes().add(closeButtonType);
	
	this.setContent(mediaView);
	this.setPrefSize(1200, 800);
	
	//Add css-styling
	this.getStylesheets().add(getClass().getResource("FocusAndHowToDialogPaneStyleSheet.css").toExternalForm());
	this.howToBorderPane.getStyleClass().add("root");
	// this.playButton.getStyleClass().add("button");
	// this.stopButton.getStyleClass().add("button");
	//this.replayButton.getStyleClass().add("button");
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public ButtonType getCloseButtonType() {
		return closeButtonType;
	}
	
	
	}
	