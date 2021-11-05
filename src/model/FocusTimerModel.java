package model;

import java.io.File;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class FocusTimerModel {
	
		// Elements
	    private Timeline timeline;
	    private final Label counterLabel;
	    private int second, minute;
	    private DecimalFormat fmt;
	    private String secondFormat, minuteFormat;
	    
	    // Constructor
	    public FocusTimerModel (Label counterLabel) {
			this.counterLabel = counterLabel;
			this.timeline = new Timeline();	
			this.fmt = new DecimalFormat("00");
			this.minute = 25;
			this.second = 0;
			
	    }

	    public void stop() {
	    	timeline.stop();
	    }
	    

	    /*
	     * --LOGIC--
	     * Starts timer and changes minutes/seconds
	     */
	    public void start() {
	      
	    	timeline.setCycleCount(Timeline.INDEFINITE);
	    	
	    	if(timeline != null) {
	    		timeline.stop();
	    	}
	    	
	    	KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
	    		
	    		@Override
	    		public void handle (ActionEvent event) {
	    			
	    			second--;
	    			secondFormat = fmt.format(second);
	    			minuteFormat = fmt.format(minute);	
					counterLabel.setText(minuteFormat + ":" + secondFormat);
					
					if (second == -1) {
						second = 59;
						minute--;
						secondFormat = fmt.format(second);
						minuteFormat = fmt.format(minute);	
						counterLabel.setText(minuteFormat + ":" + secondFormat);
					}
					if (minute == 0 && second == 0) {
						timeline.stop();				
						// Media media = new Media("src/icons/endSound.wav");
						// MediaPlayer mediaPlayer = new MediaPlayer(media);
	    		}
	    		}
	    		});
	    	timeline.getKeyFrames().add(frame);
	    	timeline.playFromStart();
	    	
	    }
	    	
	  public void restart() {
		  
		  minute = 25;
		  second = 0;
		  
		  start();
		  
	  }
	

	    // Getter and Setter

		public Label getTimerLbl() {
			return counterLabel;
		}

		public int getSecond() {
			return second;
		}

		public int getMinute() {
			return minute;
		}

		public void setSecond(int seconds) {
			this.second = second;
		}

		public void setMinute(int minute) {
			this.minute = minute;
		}

	}