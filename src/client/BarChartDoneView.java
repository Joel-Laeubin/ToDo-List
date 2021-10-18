package client;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.application.Application;
import javafx.scene.chart.XYChart;

public class BarChartDoneView {
	
	final static String done = "Done";
	final static String undone = "Undone";
	private int doneObjects;
	private int undoneObjects;
	
	private CategoryAxis xAxis;
	private NumberAxis yAxis;
	private BarChart<String, Number> bc;
	
	public BarChartDoneView() {
		this.xAxis = new CategoryAxis();
		this.yAxis = new NumberAxis();
		this.bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("Status Overview");
		xAxis.setLabel("Category");
		yAxis.setLabel("Number");
		
		XYChart.Series serie1 = new XYChart.Series();
		serie1.setName(done);
		serie1.getData().add(new XYChart.Data(done, getDoneData()));
		
		
	}

	private int getDoneData() {
		
		return 0;
	}
	
	
	

}
