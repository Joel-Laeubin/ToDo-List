package client;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.ToDo;
import model.ToDoList;

public class ToDoView extends BorderPane {
	
	// Control elements for this container
	
		protected ToDo toDoModel;
		protected ToDoList toDoListModel;

		protected ListView <String> listView;		
		protected VBox vBox;
		protected BorderPane borderPane;
		protected SplitPane splitPane;
		
		protected Dialog<ButtonType> addToDoDialog;
		protected AddToDoDialogPane toDoDialogPane;
		
		final static String done = "Done";
		final static String undone = "Undone";
		protected static int doneNumber = 0;
		protected static int undoneNumber = 0;
		
		private CategoryAxis xAxis;
		private NumberAxis yAxis;
		protected BarChart<String, Number> bc;
		protected XYChart.Series serie1;
		protected XYChart.Series serie2;
		
		protected Dialog<ButtonType> focusDialog;
		protected FocusTimer focusTimerDialog;
		protected Button openFocusTimer;
				
		protected VBox vBoxBottom;
		protected HBox hBoxBottom;
		
		/*
		 * Instantiates all necessary control elements
		 * and adds them to the container
		 */
		
		public ToDoView(ToDo toDoModel, ToDoList toDoListModel) {
			
			// Instantiates our classes
			this.toDoModel = toDoModel;
			this.toDoListModel = toDoListModel;
			
			// Creates a ListView with items and sets the active item
			this.listView = new ListView<String>();
			listView.getItems().addAll(
					"Wichtig",
					"Geplant",
					"Erledigt",
					"Papierkorb");
			listView.getSelectionModel().select(1);

			// Creates a VBox in the BorderPane and includes the listView
			this.vBox = new VBox();
			this.vBox.getChildren().addAll(listView);
			this.setLeft(this.vBox);
			
			/*
			 * Creates a BorderPane in a BorderPane
			 * This is for the view on the right side
			 */
			this.borderPane = new BorderPane();
			this.setCenter(borderPane);
			this.borderPane.setPrefSize(1000, 600);

			/*
			 * Creates a SplitPane between vBox and borderPane
			 * This SplitPane should divide the GUI in two
			 * main views (List on the left, View on the right)
			 */
			this.splitPane = new SplitPane();
			this.splitPane.getItems().addAll(vBox, borderPane);
			this.splitPane.setDividerPositions(0.3);
			this.setLeft(splitPane);

			// Customize Dialog
			this.addToDoDialog = new Dialog<ButtonType>();
			this.toDoDialogPane = new AddToDoDialogPane(listView.getItems());
			this.addToDoDialog.setDialogPane(toDoDialogPane);
			
			
			//Creating the BarChart to show the done and undone ToDo's
			this.xAxis = new CategoryAxis();
			this.yAxis = new NumberAxis();
			this.bc = new BarChart<String, Number>(xAxis, yAxis);
			
			bc.setTitle("Status Overview");
			xAxis.setLabel("Category");
			yAxis.setLabel("Number");
			bc.setAnimated(false);
			
			XYChart.Series serie1 = new XYChart.Series();
			serie1.setName(done);
			XYChart.Series serie2 = new XYChart.Series<>();
			serie2.setName(undone);
			
			
			
			Timeline Updater = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					serie1.getData().clear();
					serie2.getData().clear();
					serie1.getData().add(new XYChart.Data<String, Number>(done, doneNumber));
					serie2.getData().add(new XYChart.Data<String, Number>(undone, undoneNumber));
				}
			}));
			Updater.setCycleCount(Timeline.INDEFINITE);
			Updater.play();			
			bc.getData().addAll(serie1, serie2);
			
			this.vBox.getChildren().add(bc);
			
			/*
			 * Button Focus timer for a focus timer dialog
			 * on the right side of the bottom of the BorderPane
			 */
			this.vBoxBottom = new VBox();
			this.hBoxBottom = new HBox();
			this.openFocusTimer = new Button("Fokus Timer");
			this.hBoxBottom.getChildren().add(openFocusTimer);
			this.hBoxBottom.setPadding(new Insets(0.0, 0.0, 40.0, 950.0));
			this.vBoxBottom.getChildren().add(hBoxBottom);
			this.setBottom(vBoxBottom);
		    
			// Add CSS styling
			this.getStylesheets().add(getClass().getResource("ToDoViewStyleSheet.css").toExternalForm());
			this.getStyleClass().add("view");
			this.listView.getStyleClass().add("listView");
			this.vBox.getStyleClass().add("vBox");
			this.borderPane.getStyleClass().add("borderPane");
			this.splitPane.getStyleClass().add("splitPane");
			this.bc.getStyleClass().add("barChart");
			
			
		}
		
		private int getUndoneData() {
			int undoneCount = 0;
			for(String category : ToDoList.categoryList) {
				if(category.equals("Geplant")) {
					undoneCount++;
				if(category.equals("Wichtig")) {
					undoneCount++;
				}
				}
			}
			return undoneCount;
		}

		private int getDoneData() {
			int doneCount = 0;		
			for (String category : ToDoList.categoryList) {
				if(category.equals("Erledigt")) {
					doneCount++;
				}
			}
			return doneCount;
		}
	

		
		
		

		}
