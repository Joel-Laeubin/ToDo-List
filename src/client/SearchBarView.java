package client;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.ToDo;

public class SearchBarView extends MainBarView {

    // Constructor
    public SearchBarView(ObservableList<ToDo> searchList) {

        // Call super constructor to get defined elements
        super();

        // Individual icons and labels for this view
        this.icon = new ImageView("/icons/lupe.png");
        this.label = new Label("Suchergebnisse");
        this.icon.setFitHeight(50);
        this.icon.setFitWidth(53);
        this.header.getChildren().addAll(icon, label);

        // Populate tableView
        this.tableView.getItems().addAll(searchList);

        // Add CSS
//        this.getStylesheets().add(this.getClass().getResource("CategoryViewStyle.css").toExternalForm());
//        this.icon.getStyleClass().add("icon");
//        this.label.getStyleClass().add("label");
//        this.header.getStyleClass().add("header");

    }

}
