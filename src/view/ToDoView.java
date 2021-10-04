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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		private TabView tabView;
		
		/*
		 * instantiates all necessary control elements
		 * and adds them to the container
		 */
		
		public ToDoView() {
			
			// creating icons to be used on menu
			Image saveIcon = new Image(getClass().getResourceAsStream("speichern.png"));
			ImageView saveView = new ImageView(saveIcon);
			saveView.setFitWidth(15);
			saveView.setFitHeight(15);
			
			Image backIcon = new Image(getClass().getResourceAsStream("zurück.png"));
			ImageView backView = new ImageView(backIcon);
			backView.setFitWidth(15);
			backView.setFitHeight(15);
			
			Image furtherIcon = new Image(getClass().getResourceAsStream("wiederholen.png"));
			ImageView furtherView = new ImageView(furtherIcon);
			furtherView.setFitWidth(15);
			furtherView.setFitHeight(15);
			
			Image plusIcon = new Image(getClass().getResourceAsStream("plus.png"));
			ImageView plusView = new ImageView(plusIcon);
			plusView.setFitWidth(15);
			plusView.setFitHeight(15);
			
			Image settingsIcon = new Image(getClass().getResourceAsStream("settings.png"));
			ImageView settingsView = new ImageView(settingsIcon);
			settingsView.setFitWidth(15);
			settingsView.setFitHeight(15);
			
			// creating menus incl. icons
			
			this.menuSave = new Menu();
			this.menuSave.setGraphic(saveView);
			
			this.menuBack = new Menu();
			this.menuBack.setGraphic(backView);
			
			this.menuFurther = new Menu();
			this.menuFurther.setGraphic(furtherView);
			
			this.menuPlus = new Menu();
			this.menuPlus.setGraphic(plusView);
			
			this.menuSettings = new Menu();
			this.menuSettings.setGraphic(settingsView);
			
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
