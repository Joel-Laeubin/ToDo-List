package client;

import javafx.collections.ObservableList;
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
    protected HBox tagsBar;

    protected Label newTaskLabel;
    protected Label titleLabel;
    protected Label categoryLabel;
    protected Label dueDateLabel;
    protected Label messageLabel;
    protected Label tagsLabel;

    protected TextField titleTextfield;
    protected TextField tagsTextfield;

    protected ComboBox<String> categoryComboBox;

    protected DatePicker datePicker;

    protected TextArea messageTextArea;

    // Fields
    private final int SPACING_CATEGORYBAR = 15;
    private final int SPACING_TITLEBAR = 43;
    private final int SPACING_DUEDATEBAR = 28;


    // Constructor
    public AddToDoDialogPane(ObservableList<String> listViewItems) {

        // Instantiate components
        root = new BorderPane();
        leftPane = new VBox();
        rightPane = new VBox();
        titleBar = new HBox(SPACING_TITLEBAR);
        categoryBar = new HBox(SPACING_CATEGORYBAR);
        dueDateBar = new HBox(SPACING_DUEDATEBAR);
        tagsBar = new HBox();

        newTaskLabel = new Label("Neue Aufgabe");
        titleLabel = new Label("Titel");
        categoryLabel = new Label("Kategorie");
        dueDateLabel = new Label("Termin");
        messageLabel = new Label("Beschreibung");
        tagsLabel = new Label("Tags");

        titleTextfield = new TextField();
        tagsTextfield = new TextField();

        categoryComboBox = new ComboBox<>();
        categoryComboBox.setItems(listViewItems);
        datePicker = new DatePicker();
        messageTextArea = new TextArea();

        // Fill controls into containers
        titleBar.getChildren().addAll(titleLabel, titleTextfield);
        categoryBar.getChildren().addAll(categoryLabel, categoryComboBox);
        dueDateBar.getChildren().addAll(dueDateLabel, datePicker);
        tagsBar.getChildren().addAll(tagsLabel, tagsTextfield);

        leftPane.getChildren().addAll(titleBar, categoryBar, dueDateBar, tagsBar);
        rightPane.getChildren().addAll(messageLabel, messageTextArea);

        // Set containers
        root.setTop(newTaskLabel);
        root.setLeft(leftPane);
        root.setRight(rightPane);

        // Add CSS styling
        this.getStylesheets().add(getClass().getResource("AddToDoDialogPaneStyle.css").toExternalForm());
        newTaskLabel.setId("titleLabel");
        root.getStyleClass().add("borderPane");
        this.leftPane.getStyleClass().add("leftPane");
        this.rightPane.getStyleClass().add("rightPane");
        this.messageTextArea.getStyleClass().add("messageTextArea");
        this.getStyleClass().add("view");
        
        

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
