package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.ToDoView;

public class ToDoApp extends Application {

	// starts the JavaFX application
	public static void main(String[] args) {
		launch(args);
	}
	
	// shows a GUI for the ToDo-App
	
	public void start(Stage stage) {
		
		// 1. instantiates the root
		ToDoView root = new ToDoView();
		
		// 2. passes the root to the scene
		Scene scene = new Scene(root);
		
		// 3. shows scene in a window (object stage)
		stage.setScene(scene);
		stage.setTitle("ToDo-App");
		stage.show();
		
		// adds an icon to the window
		Image doneImage = new Image("/icons/doneIcon.png");
		stage.getIcons().add(doneImage);
		
		
	}

}