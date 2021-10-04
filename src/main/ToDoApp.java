package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToDoApp extends Application {

	// starts the JavaFX application
	public static void main(String[] args) {
		launch(args);
	}
	
	// shows a GUI for the ToDo-App
	
	public void start(Stage stage) {
		
		// 1. instantiate the root
		ToDoView root = new ToDoView();
		
		// 2. pass the root to the scene
		Scene scene = new Scene(root);
		
		// 3. shows scene in a window (object stage)
		stage.setScene(scene);
		stage.setTitle("ToDo-App");
		stage.show();
		
	}

}
