package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ToDoView extends BorderPane {
	
	// control elements for this container
	
	private Menu menuSave;
	private Menu menuBack;
	private Menu menuFurther;
	private Menu menuPlus;
	private MenuBar menuBar;
	
	private VBox vBox;
	private SplitPane splitPane;
	private Label name;
	private Button important;
	private Button planned;
	private Button done;
	private Button deleted;
	private Button newFolder;
	
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
		this.menuSave = new Menu("Icon Speichern");
		this.menuBack = new Menu("Icon zurück");
		this.menuFurther = new Menu("Icon vorwärts");
		this.menuPlus = new Menu("Icon plus");
		
		this.menuBar = new MenuBar();
		this.menuBar.getMenus().addAll(this.menuSave, this.menuBack, this.menuFurther, this.menuPlus);
		
		this.setTop(this.menuBar);
		
		this.name = new Label("xxx");
		this.important = new Button("Wichtig");
		this.planned = new Button("Geplant");
		this.done = new Button("erledigt");
		this.deleted = new Button("Papierkorb");
		this.newFolder = new Button("neuer Ordner");
		
		this.vBox = new VBox();
		this.vBox.getChildren().addAll(
				this.name, 
				this.important, 
				this.planned, 
				this.done, 
				this.deleted, 
				this.newFolder);
		this.setLeft(this.vBox);
		
		this.searchField = new TextField();
		this.createToDo = new Button("+");
		this.hBox = new HBox();
		this.hBox.getChildren().addAll(this.searchField, this.createToDo);
		this.setCenter(this.hBox);

		this.splitPane = new SplitPane();
		this.splitPane.getItems().addAll(vBox, hBox);
		this.setCenter(splitPane);
	}
	
	

}
