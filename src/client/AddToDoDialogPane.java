package client;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddToDoDialogPane extends DialogPane {

    // Components
    protected BorderPane root;
    protected VBox leftPane;
    protected VBox rightPane;
    protected HBox titleBar;
    protected HBox categoryBar;
    protected HBox dueDateBar;


    protected Label newTask;
    protected Label title;
    protected Label category;
    protected Label dueDate;
    protected Label message;

    protected TextField titleTextfield;

    protected ComboBox categoryComboBox;

    protected DatePicker datePicker;

    protected TextArea messageTextArea;


    // Constructor
    public AddToDoDialogPane() {

        // Add buttonTypes
        this.getButtonTypes().add(new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE));
        this.getButtonTypes().add(new ButtonType("Erstellen", ButtonBar.ButtonData.OK_DONE));

        // Instantiate
        this.root = new BorderPane();

        this.setContent(root);

    }

}
