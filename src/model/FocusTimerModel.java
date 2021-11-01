package model;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class FocusTimerModel {
	
	 private boolean isRunning = false;
	    private boolean isBreak = false;
	    private Timer timer = new Timer();
	    private int counter = 60 * 25; // 60 * 25 Testing:10
	    private final Label counterLabel;
	    private int seconds, minutes;

	    public FocusTimerModel (Label counterLabel) {
			this.counterLabel = counterLabel;
	    }

	    public boolean isRunning() {
	        return isRunning;
	    }

	    public void setRunning(boolean running) {
	        isRunning = running;
	    }

	    public void pause() {
	        timer.cancel();
	    }

	    public void start() {
	        timer = new Timer();
	        timer.scheduleAtFixedRate(new TimerTask() {
	            @Override
	            public void run() {
	                Platform.runLater(() -> {
	                    counter--;
	                    seconds = counter % 60;
	                    minutes = counter / 60;
	                    if (seconds < 10 && minutes < 10) {
	                    	counterLabel.setText("0" + minutes + ":0" + seconds);
	                    } else if (minutes < 10) {
	                    	counterLabel.setText("0" + minutes + ":" + seconds);
	                    } else {
	                    	counterLabel.setText(minutes + ":" + seconds);
	                    }
	                });
	            }
	        }, 0, 1500);
	    }
	    
	    public void restart() {
	    	isRunning();
	    	setRunning(true);
	    	
	    	if (minutes == 25 && seconds == 0) {
	    		counterLabel.setText(minutes + ":00");
	    	}
	    	
	    	counter = (60 * 25) + 1;
	    	
	    	seconds = counter % 60;
	        minutes = counter / 60;
	       
	        timer = new Timer();
	        timer.scheduleAtFixedRate(new TimerTask() {
	            @Override
	            public void run() {
	                Platform.runLater(() -> {
	                    counter--;

	                    seconds = counter % 60;
	                    minutes = counter / 60;
	                    if (seconds < 10 && minutes < 10) {
	                    	counterLabel.setText("0" + minutes + ":0" + seconds);
	                    } else if (minutes < 10) {
	                    	counterLabel.setText("0" + minutes + ":" + seconds);
	                    } else {
	                    	counterLabel.setText(minutes + ":" + seconds);
	                    }
	                });
	            }
	        }, 0, 1000);
	    }
	    	

		public boolean isBreak() {
			return isBreak;
		}

		public Timer getTimer() {
			return timer;
		}

		public int getCounter() {
			return counter;
		}

		public Label getTimerLbl() {
			return counterLabel;
		}

		public int getSeconds() {
			return seconds;
		}

		public int getMinutes() {
			return minutes;
		}

		public void setBreak(boolean isBreak) {
			this.isBreak = isBreak;
		}

		public void setTimer(Timer timer) {
			this.timer = timer;
		}

		public void setCounter(int counter) {
			this.counter = counter;
		}

		public void setSeconds(int seconds) {
			this.seconds = seconds;
		}

		public void setMinutes(int minutes) {
			this.minutes = minutes;
		}

	}