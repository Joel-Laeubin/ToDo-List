package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ToDoView extends BorderPane {
	
	// control elements for this container
	
		private Menu menuSave;
		private Menu menuBack;
		private Menu menuFurther;
		private Menu menuPlus;
		private Menu menuSettings;
		private MenuBar menuBar;
		
		private VBox vBox;
		private SplitPane splitPane;
		private Label name;
		
		private Label importantTitle;
		private Label plannedTitle;
		private Label doneTitle;
		private Label deletedTitle;
		
		private TextField searchField;
		private Button createToDo;
		
		private HBox hBox;
		
		/*
		 * instantiates all necessary control elements
		 * and adds them to the container
		 */
		
		public ToDoView() {
			
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
			ListView<String> listView = new ListView<>();
			listView.getItems().addAll(
					"Wichtig",
					"Geplant",
					"Erledigt",
					"Papierkorb",
					"Neuer Ordner");
			
			/*
			 * creates a VBox in the BorderPane
			 * and includes the listView
			 */
			this.vBox = new VBox();
			this.vBox.getChildren().addAll(listView);
			this.setLeft(this.vBox);
			
			/*
			 * creates a TextField and a Button
			 * in a HBox, which is in the Center of the
			 * BorderPane					
			 */
			this.searchField = new TextField();
			this.createToDo = new Button("+");
			this.hBox = new HBox();
			this.hBox.getChildren().addAll(this.searchField, this.createToDo);
			this.setCenter(this.hBox);
			
			// creates a SplitPane between the vBox and HBox
			this.splitPane = new SplitPane();
			this.splitPane.getItems().addAll(vBox, hBox);
			this.setCenter(splitPane);
		
		}
		

}
