package client;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
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

		
		/*
		 * Instantiates all necessary control elements
		 * and adds them to the container
		 */
		
		public ToDoView(ToDo toDoModel, ToDoList toDoListModel) {
			
			// Instantiates our classes
			this.toDoModel = toDoModel;
			this.toDoListModel = toDoListModel;
			
			// Creates a ListView with items
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
			this.borderPane.setPrefSize(1000, 900);

			/*
			 * Creates a SplitPane between vBox and borderPane
			 * This SplitPane should divide the GUI in two
			 * main views (List on the left, View on the right)
			 */
			this.splitPane = new SplitPane();
			this.splitPane.getItems().addAll(vBox, borderPane);
			this.setLeft(splitPane);

			// Customize Dialog
			this.addToDoDialog = new Dialog<ButtonType>();
			this.toDoDialogPane = new AddToDoDialogPane(listView.getItems());
			this.addToDoDialog.setDialogPane(toDoDialogPane);
		
			this.getStylesheets().add(getClass().getResource("ToDoViewStyleSheet.css").toExternalForm());
		}
		

}
