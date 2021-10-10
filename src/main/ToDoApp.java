package main;

import client.ToDoController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import client.ToDoView;
import model.ToDo;
import model.ToDoList;

public class ToDoApp extends Application {

	// Fields
	private ToDo todoModel;
	private ToDoList toDoList;
	private ToDoView toDoView;
	private ToDoController toDoController;

	// starts the JavaFX application
	public static void main(String[] args) {
		launch(args);
	}
	
	// shows a GUI for the ToDo-App
	
	public void start(Stage stage) {
		
		// 1. instantiates the root todoView
		this.todoModel = new ToDo();
		this.toDoList = new ToDoList();
		this.toDoView = new ToDoView(todoModel, toDoList);
		this.toDoController = new ToDoController(this.toDoView, this.todoModel, toDoList);
		
		// 2. passes the root to the scene
		Scene scene = new Scene(toDoView);
		
		// 3. shows scene in a window (object stage)
		stage.setScene(scene);
		stage.setTitle("ToDo-App");
		stage.show();
		
		// adds an icon to the window
		Image doneImage = new Image("/icons/doneIcon.png");
		stage.getIcons().add(doneImage);
		
		
	}

}
