package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class FocusTimerDialogPane extends JFrame {
	
	JFrame window;
	JLabel counterLabel;
	Font counterFont;	
	Font buttonFont;
	Timer timer;	
	int second, minute;
	String secondDecimalFormat, minuteDecimalFormat;	
	DecimalFormat decimalFormat = new DecimalFormat("00");
	VBox counterVBox;
	
	JButton start;
	JButton stop;
	JButton restart;
	

	public FocusTimerDialogPane() {
		
		// JFrame settings
				this.setSize(600, 450);
				this.setLayout(null);
				this.setVisible(true);
				// this.setResizable(false); -- CHANGE
				
				// Fonts for Label and Button
				this.counterFont = new Font("Arial", Font.PLAIN, 60);
				this.buttonFont = new Font("Arial", Font.PLAIN, 14);
				
				// Label settings
				counterLabel = new JLabel("");
				counterLabel.setBounds(200, 130, 200, 100);
				counterLabel.setHorizontalAlignment(JLabel.CENTER);
				counterLabel.setFont(counterFont);
				
				// Add Label to JFrame
				this.add(counterLabel);
				
				// Countdown timer
				counterLabel.setText("25:00");
				second = 0;
				minute = 25;
				focusTimer();
				timer.start();	
				
				// Buttons
				this.start = new JButton("Start");
				this.start.setLayout(null);
				this.start.setBounds(100, 250, 300, 250);
				this.start.setHorizontalAlignment(JLabel.CENTER);
				this.start.setFont(buttonFont);
				
				this.stop = new JButton("Stop");
				this.stop.setLayout(null);
				this.start.setBounds(200, 250, 200, 250);
				this.start.setHorizontalAlignment(JLabel.CENTER);
				this.start.setFont(buttonFont);
				
				this.restart = new JButton("Restart");
				this.restart.setLayout(null);
				this.restart.setBounds(300, 250, 100, 250);
				this.restart.setHorizontalAlignment(JLabel.CENTER);
				this.restart.setFont(buttonFont);
				
				// Add buttons to JFrame
				this.add(start);
				this.add(stop);
				this.add(restart);	
				
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
	
	public void startJButton() {
		this.start.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				timer.start();
			}
		});
	}
	
	public void stopJButton() {
		this.start.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				timer.stop();
				
			}
		});
	}
		
	public void restartJButton() {
		this.start.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				timer.restart();
			}
		});
	
}		

			
}