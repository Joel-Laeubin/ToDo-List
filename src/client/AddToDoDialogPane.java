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

    protected Tooltip titleToolTip;
    protected Tooltip messageToolTip;
    protected Tooltip categoryToolTip;
    protected Tooltip dateToolTip;
    protected Tooltip tagsToolTip;

    protected ComboBox<String> categoryComboBox;

    protected DatePicker datePicker;

    protected TextArea messageTextArea;

    // Custom button type for eventhandling
    ButtonType okButtonType;

    // Fields
    private final int SPACING_CATEGORYBAR = 15;
    private final int SPACING_TITLEBAR = 43;
    private final int SPACING_DUEDATEBAR = 28;
    private final int SPACING_TAGSBAR = 40;


    // Constructor
    public AddToDoDialogPane(ObservableList<String> listViewItems) {

        // Instantiate components
        root = new BorderPane();
        leftPane = new VBox();
        rightPane = new VBox();
        titleBar = new HBox(SPACING_TITLEBAR);
        categoryBar = new HBox(SPACING_CATEGORYBAR);
        dueDateBar = new HBox(SPACING_DUEDATEBAR);
        tagsBar = new HBox(SPACING_TAGSBAR);

        newTaskLabel = new Label("Neue Aufgabe");
        titleLabel = new Label("Titel");
        categoryLabel = new Label("Kategorie");
        dueDateLabel = new Label("Termin");
        messageLabel = new Label("Beschreibung");
        tagsLabel = new Label("Tags");

        titleTextfield = new TextField();
        tagsTextfield = new TextField();

        // Instantiate tooltips
        titleToolTip = new Tooltip("Your title must be > 50 characters.");
        messageToolTip = new Tooltip("Your title must be > 300 characters.");
        categoryToolTip = new Tooltip("Your category must be a value contained in the dropdown.");
        dateToolTip = new Tooltip("Your date must be in format DD.MM.YYYY and lie ahead in time.");
        tagsToolTip = new Tooltip("Your tags must be single words separated with a semicolon (;).");

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

        // Associate tooltips
        titleLabel.setTooltip(titleToolTip);
        messageLabel.setTooltip(messageToolTip);
        categoryLabel.setTooltip(categoryToolTip);
        dueDateLabel.setTooltip(dateToolTip);
        tagsLabel.setTooltip(tagsToolTip);

        // Add CSS styling
        this.getStylesheets().add(getClass().getResource("AddToDoDialogPaneStyle.css").toExternalForm());
        newTaskLabel.setId("titleLabel");
        root.getStyleClass().add("borderPane");
        this.leftPane.getStyleClass().add("leftPane");
        this.rightPane.getStyleClass().add("rightPane");
        this.messageTextArea.getStyleClass().add("messageTextArea");
        this.getStyleClass().add("view");

        // Add buttonTypes
        okButtonType = new ButtonType("Erstellen", ButtonBar.ButtonData.OK_DONE);
        this.getButtonTypes().add(new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE));
        this.getButtonTypes().add(okButtonType);

        // Set content
        this.setContent(root);

    }

    // Clearing method
    public void clearPane() {
        this.titleTextfield.clear();
        this.categoryComboBox.valueProperty().setValue(null);
        this.datePicker.getEditor().clear();
        this.messageTextArea.clear();
        this.tagsTextfield.clear();
    }

}
