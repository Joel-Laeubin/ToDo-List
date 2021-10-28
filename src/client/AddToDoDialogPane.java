package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.ToDo;

public class AddToDoDialogPane extends DialogPane {

    // Components
    protected BorderPane root;
    protected VBox leftPane;
    protected VBox rightPane;
    protected HBox titleBar;
    protected HBox categoryBar;
    protected HBox dueDateBar;
    protected HBox tagsBar;
    protected VBox headerBar;

    protected Label newTaskLabel;
    protected Label tippLabel;
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
    private final int SPACING_HEADERBAR = -10;
    private final Duration DURATION_UNTIL_SHOW = Duration.seconds(0.4);


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
        headerBar = new VBox(SPACING_HEADERBAR);

        newTaskLabel = new Label("Neue Aufgabe");
        titleLabel = new Label("Titel");
        categoryLabel = new Label("Kategorie");
        dueDateLabel = new Label("Termin");
        messageLabel = new Label("Beschreibung");
        tagsLabel = new Label("Tags");
        tippLabel = new Label("Bewegen Sie Ihren Mauszeiger über einen Schriftzug!");

        titleTextfield = new TextField();
        tagsTextfield = new TextField();

        // Instantiate tooltips
        titleToolTip = new Tooltip("Your title must be > 50 characters.");
        messageToolTip = new Tooltip("Your title must be > 300 characters.");
        categoryToolTip = new Tooltip("Your category must be a value contained in the dropdown.");
        dateToolTip = new Tooltip("Your date must be in format DD.MM.YYYY and lie ahead in time.");
        tagsToolTip = new Tooltip("Your tags must be single words separated with a semicolon (;).");

        // Change tooltip timers
        titleToolTip.setShowDelay(DURATION_UNTIL_SHOW);
        messageToolTip.setShowDelay(DURATION_UNTIL_SHOW);
        categoryToolTip.setShowDelay(DURATION_UNTIL_SHOW);
        dateToolTip.setShowDelay(DURATION_UNTIL_SHOW);
        tagsToolTip.setShowDelay(DURATION_UNTIL_SHOW);

        // Instantiate the rest of the items
        categoryComboBox = new ComboBox<>();
        ObservableList<String> copy = FXCollections.observableArrayList();
        copy.addAll(listViewItems);
        copy.remove(3);
        categoryComboBox.setItems(copy);
        datePicker = new DatePicker();
        messageTextArea = new TextArea();

        // Fill controls into containers
        titleBar.getChildren().addAll(titleLabel, titleTextfield);
        categoryBar.getChildren().addAll(categoryLabel, categoryComboBox);
        dueDateBar.getChildren().addAll(dueDateLabel, datePicker);
        tagsBar.getChildren().addAll(tagsLabel, tagsTextfield);
        headerBar.getChildren().addAll(newTaskLabel, tippLabel);

        leftPane.getChildren().addAll(titleBar, categoryBar, dueDateBar, tagsBar);
        rightPane.getChildren().addAll(messageLabel, messageTextArea);

        // Set containers
        root.setTop(headerBar);
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
        tippLabel.setId("titleMessage");
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

    // Constructor overload method used for updating an item
    public AddToDoDialogPane(ObservableList<String> listViewItems, ToDo todo) {

        // Instantiate components
        root = new BorderPane();
        leftPane = new VBox();
        rightPane = new VBox();
        titleBar = new HBox(SPACING_TITLEBAR);
        categoryBar = new HBox(SPACING_CATEGORYBAR);
        dueDateBar = new HBox(SPACING_DUEDATEBAR);
        tagsBar = new HBox(SPACING_TAGSBAR);
        headerBar = new VBox(SPACING_HEADERBAR);

        newTaskLabel = new Label("Neue Aufgabe");
        titleLabel = new Label("Titel");
        categoryLabel = new Label("Kategorie");
        dueDateLabel = new Label("Termin");
        messageLabel = new Label("Beschreibung");
        tagsLabel = new Label("Tags");
        tippLabel = new Label("Bewegen Sie Ihren Mauszeiger über einen Schriftzug!");

        titleTextfield = new TextField();
        tagsTextfield = new TextField();

        // Instantiate tooltips
        titleToolTip = new Tooltip("Your title must be > 50 characters.");
        messageToolTip = new Tooltip("Your title must be > 300 characters.");
        categoryToolTip = new Tooltip("Your category must be a value contained in the dropdown.");
        dateToolTip = new Tooltip("Your date must be in format DD.MM.YYYY and lie ahead in time.");
        tagsToolTip = new Tooltip("Your tags must be single words separated with a semicolon (;).");

        // Change tooltip timers
        titleToolTip.setShowDelay(DURATION_UNTIL_SHOW);
        messageToolTip.setShowDelay(DURATION_UNTIL_SHOW);
        categoryToolTip.setShowDelay(DURATION_UNTIL_SHOW);
        dateToolTip.setShowDelay(DURATION_UNTIL_SHOW);
        tagsToolTip.setShowDelay(DURATION_UNTIL_SHOW);

        // Instantiate the rest of the items
        categoryComboBox = new ComboBox<>();
        categoryComboBox.setItems(listViewItems);
        datePicker = new DatePicker();
        messageTextArea = new TextArea();

        // Fill controls into containers
        titleBar.getChildren().addAll(titleLabel, titleTextfield);
        categoryBar.getChildren().addAll(categoryLabel, categoryComboBox);
        dueDateBar.getChildren().addAll(dueDateLabel, datePicker);
        tagsBar.getChildren().addAll(tagsLabel, tagsTextfield);
        headerBar.getChildren().addAll(newTaskLabel, tippLabel);

        leftPane.getChildren().addAll(titleBar, categoryBar, dueDateBar, tagsBar);
        rightPane.getChildren().addAll(messageLabel, messageTextArea);

        // Set containers
        root.setTop(headerBar);
        root.setLeft(leftPane);
        root.setRight(rightPane);

        // Associate tooltips
        titleLabel.setTooltip(titleToolTip);
        messageLabel.setTooltip(messageToolTip);
        categoryLabel.setTooltip(categoryToolTip);
        dueDateLabel.setTooltip(dateToolTip);
        tagsLabel.setTooltip(tagsToolTip);

        // Fill fields
        titleTextfield.setText(todo.getTitle());
        categoryComboBox.getEditor().setText(todo.getCategory());
        datePicker.getEditor().setText(todo.getDueDate().toString());

        // Fill tag field, create new tag string if tags are set on item
        if(!todo.getTags().isEmpty()) {
            StringBuilder tagString = new StringBuilder();
            for(String tag : todo.getTags()) { tagString.append(tag).append("; "); }
            tagsTextfield.setText(tagString.toString());
        }

        // Debugging tag string - if it's empty it will insert a semicolon
        if(tagsTextfield.getText().equals("; ")) { tagsTextfield.clear(); }
        messageTextArea.setText(todo.getMessage());

        // Add CSS styling
        this.getStylesheets().add(getClass().getResource("AddToDoDialogPaneStyle.css").toExternalForm());
        newTaskLabel.setId("titleLabel");
        tippLabel.setId("titleMessage");
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
