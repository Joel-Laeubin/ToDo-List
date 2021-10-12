package client;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ToDo;
import model.ToDoList;

public class ToDoView extends BorderPane {
	
	// control elements for this container
	
		protected Menu menuSave;
		protected Menu menuBack;
		protected Menu menuFurther;
		protected Menu menuPlus;
		protected Menu menuSettings;
		protected MenuBar menuBar;
		
		protected VBox vBox;
		protected SplitPane splitPane;
		protected Label name;
		
		protected Label importantTitle;
		protected Label plannedTitle;
		protected Label doneTitle;
		protected Label deletedTitle;
		
		protected TextField searchField;
		
		protected HBox hBox;

		protected Dialog<ButtonType> addToDoDialog;

		protected ToDo toDoModel;
		protected ToDoList toDoListModel;
		protected ListView <String> listView;
		
		/*
		 * instantiates all necessary control elements
		 * and adds them to the container
		 */
		
		public ToDoView(ToDo toDoModel, ToDoList toDoListModel) {

			this.toDoModel = toDoModel;
			this.toDoListModel = toDoListModel;

			this.menuSave = new Menu();
			
			this.menuBack = new Menu();
						
			this.menuFurther = new Menu();
			
			this.menuPlus = new Menu();
			
			this.menuSettings = new Menu();
			
			// set the menus to the menubar
			this.menuBar = new MenuBar();
			this.menuBar.getMenus().addAll(this.menuSave, this.menuBack, this.menuFurther, this.menuPlus);
			
			// set the menuBar on the top of the BorderPane
			this.setTop(this.menuBar);
			
			// creates a ListView
			this.listView = new ListView<>();
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
			
			// creates a SplitPane between the vBox and HBox
			this.splitPane = new SplitPane();
			this.splitPane.getItems().addAll(vBox, hBox);
			this.setLeft(splitPane);

			// Customize Dialog
			this.addToDoDialog = new Dialog<ButtonType>();
			this.addToDoDialog.setDialogPane(new AddToDoDialogPane());
		
		}
		

}
