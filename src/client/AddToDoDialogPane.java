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

    protected Label newTaskLabel;
    protected Label titleLabel;
    protected Label categoryLabel;
    protected Label dueDateLabel;
    protected Label messageLabel;

    protected TextField titleTextfield;

    protected ComboBox<String> categoryComboBox;

    protected DatePicker datePicker;

    protected TextArea messageTextArea;

    // Fields
    private final int SPACING_CATEGORYBAR = 15;
    private final int SPACING_TITLEBAR = 43;
    private final int SPACING_DUEDATEBAR = 28;


    // Constructor
    public AddToDoDialogPane() {

        // Instantiate components
        root = new BorderPane();
        leftPane = new VBox();
        rightPane = new VBox();
        titleBar = new HBox(SPACING_TITLEBAR);
        categoryBar = new HBox(SPACING_CATEGORYBAR);
        dueDateBar = new HBox(SPACING_DUEDATEBAR);

        newTaskLabel = new Label("Neue Aufgabe");
        titleLabel = new Label("Titel");
        categoryLabel = new Label("Kategorie");
        dueDateLabel = new Label("Termin");
        messageLabel = new Label("Beschreibung");

        titleTextfield = new TextField();
        categoryComboBox = new ComboBox<>();
        datePicker = new DatePicker();
        messageTextArea = new TextArea();

        // Fill controls into containers
        titleBar.getChildren().addAll(titleLabel, titleTextfield);
        categoryBar.getChildren().addAll(categoryLabel, categoryComboBox);
        dueDateBar.getChildren().addAll(dueDateLabel, datePicker);

        leftPane.getChildren().addAll(titleBar, categoryBar, dueDateBar);
        rightPane.getChildren().addAll(messageLabel, messageTextArea);

        // Set containers
        root.setTop(newTaskLabel);
        root.setLeft(leftPane);
        root.setRight(rightPane);

        // Add CSS styling
        newTaskLabel.setId("titleLabel");
        leftPane.setId("contentPanes");
        rightPane.setId("contentPanes");
        this.getStylesheets().add(getClass().getResource("AddToDoDialogPaneStyle.css").toExternalForm());

        // Add buttonTypes
        this.getButtonTypes().add(new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE));
        this.getButtonTypes().add(new ButtonType("Erstellen", ButtonBar.ButtonData.OK_DONE));

        // Set content
        this.setContent(root);

    }

    // Clearing method
    public void clearPane() {
        this.titleTextfield.clear();
        this.categoryComboBox.getEditor().clear();
        this.datePicker.getEditor().clear();
        this.messageTextArea.clear();
    }

}
