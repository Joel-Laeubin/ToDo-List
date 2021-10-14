package client;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.ToDo;
import model.ToDoList;

public class ToDoView extends BorderPane {
	
	// control elements for this container
	
		protected ToDo toDoModel;
		protected ToDoList toDoListModel;

		protected ListView <String> listView;		
		protected VBox vBox;
		protected BorderPane borderPane;
		protected SplitPane splitPane;
		
		protected Dialog<ButtonType> addToDoDialog;
		protected AddToDoDialogPane toDoDialogPane;

		
		/*
		 * instantiates all necessary control elements
		 * and adds them to the container
		 */
		
		public ToDoView(ToDo toDoModel, ToDoList toDoListModel) {
			
			// instantiates our classes
			this.toDoModel = toDoModel;
			this.toDoListModel = toDoListModel;
			
			// creates a ListView with items
			this.listView = new ListView<String>();
			listView.getItems().addAll(
					"Wichtig",
					"Geplant",
					"Erledigt",
					"Papierkorb");			
			
			/*
			 * creates a VBox in the BorderPane
			 * and includes the listView
			 */
			this.vBox = new VBox();
			this.vBox.getChildren().addAll(listView);
			this.setLeft(this.vBox);
			
			this.borderPane = new BorderPane();
			this.setCenter(borderPane);
			this.borderPane.setPrefSize(1000, 900);

			// creates a SplitPane between the vBox and HBox
			this.splitPane = new SplitPane();
			this.splitPane.getItems().addAll(vBox, borderPane);
			this.setLeft(splitPane);

			// Customize Dialog
			this.addToDoDialog = new Dialog<ButtonType>();
			this.toDoDialogPane = new AddToDoDialogPane();
			this.addToDoDialog.setDialogPane(toDoDialogPane);
		
		}
		

}
