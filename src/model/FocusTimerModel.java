package model;

import java.text.DecimalFormat;

import client.FocusTimerDialogPane;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class FocusTimerModel {
	
	FocusTimerDialogPane dialog;
	int second;
	int minute;
	DecimalFormat fmt;
	String secondFormat;
	String minuteFormat;

public void FocusTimerDialogPane () {
	
	this.second = 0;
	this.minute = 25;
	this.dialog.getCounter();
	countDown();
	
	this.dialog = new FocusTimerDialogPane();
	
	
}	
	
public void countDown() {
	
	this.dialog.getTimeline().getKeyFrames().add(new KeyFrame(Duration.seconds(1), e ->
	{
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
					dialog.getTimeline().stop();
				}
	
		}));	
	}
}


