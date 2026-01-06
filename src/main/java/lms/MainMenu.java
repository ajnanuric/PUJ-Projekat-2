package lms;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    public void show(Stage stage) {
        Label label = new Label("Main Menu");
        VBox layout = new VBox(label);
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }
}