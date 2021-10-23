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
		
		
	}
	
}