package model;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Timer;

import client.FocusTimerDialogPane;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class FocusTimerModel {
	
	FocusTimerDialogPane dialog;
	int second;
	int minute;
	DecimalFormat fmt;
	String secondFormat;
	String minuteFormat;
	Timer timer;

public void FocusTimerDialogPane () {
	
	this.second = 0;
	this.minute = 25;
	this.dialog.getCounter();
	
	this.dialog = new FocusTimerDialogPane();
	
}
	public void countDown() {
	
	this.timer = new Timer(1500, new ActionListener() {
			
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			// TODO Auto-generated method stub
							
				second--;
				secondFormat = fmt.format(second);
				minuteFormat = fmt.format(minute);	
				dialog.getCounter().setText(minuteFormat + ":" + secondFormat);
				
				if (second == -1) {
					second = 59;
					minute--;
					secondFormat = fmt.format(second);
					minuteFormat = fmt.format(minute);	
					dialog.getCounter().setText(minuteFormat + ":" + secondFormat);
				}
				if (minute == 0 && second == 0) {
					timer.stop();
				}
		}
	});
	}


	public Timer getTimer() {
		return timer;
	}
	
	public int getSecond() {
		return second;
	}
	
	public int getMinute() {
		return minute;
	}
	
	/*
	 * Eventhandling for the start button.
	 * If the user clicks on the start button,
	 * the timer will start.
	 */
	public void startTimer() {
		this.dialog.getPlayButton().addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent actionEvent) {
				timer.start();
			}
		});
		
	}
	
	/*
	 * Eventhandling for the stop button.
	 * If the user clicks on the stop button,
	 * the timer will stop.
	 */
	public void stopTimer() {
		this.dialog.getStopButton().addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent actionEvent) {
				timer.stop();
			}
		});
}
	
	/*
	 * Eventhandling for the restart button.
	 * If the user clicks on the restart button,
	 * the timer will be set on 25 minutes and
	 * is ready for a new start.
	 */
	public void replayTimer() {
		this.dialog.getReplayButton().addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent actionEvent) {
				timer.restart();
				}
			});
			
		}
}
					
	




