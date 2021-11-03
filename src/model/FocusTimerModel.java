package model;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class FocusTimerModel {
	
		// Elements
	 	private boolean isRunning;
	    private boolean isBreak;
	    private Timer timer;
	    private int counter;
	    private final Label counterLabel;
	    private int seconds, minutes;
	    
	    // Constructor
	    public FocusTimerModel (Label counterLabel) {
			this.counterLabel = counterLabel;
			
			this.isRunning = false;
			this.isBreak = false;
			this.timer = new Timer();
			this.counter = 60 * 25; // 25 minutes * 60 = 1500 seconds
						
	    }

	    // Shows if timer is currently running
	    public boolean isRunning() {
	        return isRunning;
	    }
	    
	    // Runs timer
	    public void setRunning(boolean running) {
	        isRunning = running;
	    }
	    
	    // Pauses timer
	    public void pause() {
	        timer.cancel();
	    }

	    /*
	     * --LOGIC--
	     * Starts timer and changes minutes/seconds
	     */
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
	    
	    // Sets timer back to 25 minutes and starts running again
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
	    	

	    // Getter and Setter
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