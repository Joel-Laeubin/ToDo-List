package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import javafx.scene.layout.VBox;

public class FocusTimer extends JFrame {
	
	JLabel counterLabel;
	Font counterFont;	
	Timer timer;	
	int second, minute;
	String secondDecimalFormat, minuteDecimalFormat;	
	DecimalFormat decimalFormat = new DecimalFormat("00");
	VBox counterVBox;
	
	JButton start;
	JButton stop;
	JButton restart;
	
	ImageIcon startIcon;
	ImageIcon stopIcon;
	ImageIcon restartIcon;
	

	public FocusTimer() {
		
		// JFrame settings
				this.setSize(600, 450);
				this.setLayout(null);
				this.setResizable(false);
				this.revalidate();
				
				// Fonts for Label
				this.counterFont = new Font("Arial", Font.PLAIN, 60);
				
				// Label settings
				this.counterLabel = new JLabel("Fokus Timer");
				this.counterLabel.setBounds(200, 130, 200, 100);
				this.counterLabel.setHorizontalAlignment(JLabel.CENTER);
				this.counterLabel.setFont(counterFont);
				
				// Add Label to JFrame
				this.add(counterLabel);
				
				// Countdown timer
				this.counterLabel.setText("25:00");
				this.second = 0;
				this.minute = 25;
				focusTimer();
				//timer.start();	
				
				// Buttons
				this.startIcon = new ImageIcon(getClass().getResource("/icons/startIcon.png"));
				this.startIcon.setImage(this.startIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
				this.start = new JButton(startIcon);
				this.start.setLayout(null);
				this.start.setLocation(180, 280);
				this.start.setSize(30, 30);
				startButton();
				
				this.stopIcon = new ImageIcon(getClass().getResource("/icons/stopIcon.png"));
				this.stopIcon.setImage(this.stopIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
				this.stop = new JButton(stopIcon);
				this.stop.setLayout(null);
				this.stop.setLocation(280, 280);
				this.stop.setSize(30, 30);
				stopButton();
				
				this.restartIcon = new ImageIcon(getClass().getResource("/icons/restartIcon.png"));
				this.restartIcon.setImage(this.restartIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
				this.restart = new JButton(restartIcon);
				this.restart.setLayout(null);
				this.restart.setLocation(380, 280);
				this.restart.setSize(30, 30);
				restartButton();

				
				// Add buttons to JFrame
				this.add(start);
				this.add(stop);
				this.add(restart);	
				
				this.setVisible(true);
	} 
	/*
	 * Counts minute and seconds
	 * from 25 minutes backwards
	 */
	
	public void focusTimer() {
		
		this.timer = new Timer(1500, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				second--;
				secondDecimalFormat = decimalFormat.format(second);
				minuteDecimalFormat = decimalFormat.format(minute);	
				counterLabel.setText(minuteDecimalFormat + ":" + secondDecimalFormat);
				
				if (second == -1) {
					second = 59;
					minute--;
					secondDecimalFormat = decimalFormat.format(second);
					minuteDecimalFormat = decimalFormat.format(minute);	
					counterLabel.setText(minuteDecimalFormat + ":" + secondDecimalFormat);
				}
				if (minute == 0 && second == 0) {
					timer.stop();
				}
			}
		});		
		
	}
		
	public void startButton() {
		this.start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.start();
			}
		});
		
	}
	
		public void stopButton() {
			this.stop.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					timer.stop();
				}
			});	
}
		public void restartButton() {
			this.restart.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					counterLabel.setText("25:00");
					second = 0;
					minute = 25;
					focusTimer();
				}
			});
			
		}

		public JLabel getCounterLabel() {
			return counterLabel;
		}

		public Font getCounterFont() {
			return counterFont;
		}

		public Timer getTimer() {
			return timer;
		}

		public int getSecond() {
			second--;
			return second;
		}

		public int getMinute() {
			return minute;
		}

		public String getSecondDecimalFormat() {
			return secondDecimalFormat;
		}

		public String getMinuteDecimalFormat() {
			return minuteDecimalFormat;
		}

		public DecimalFormat getDecimalFormat() {
			return decimalFormat;
		}

		public VBox getCounterVBox() {
			return counterVBox;
		}

		public JButton getStart() {
			return start;
		}

		public JButton getStop() {
			return stop;
		}

		public JButton getRestart() {
			return restart;
		}

		public ImageIcon getStartIcon() {
			return startIcon;
		}

		public ImageIcon getStopIcon() {
			return stopIcon;
		}

		public ImageIcon getRestartIcon() {
			return restartIcon;
		}
		
		
}	