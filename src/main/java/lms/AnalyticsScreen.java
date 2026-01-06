package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnalyticsScreen {

    public void show(Stage stage) {

        Label label = new Label("Analytics");
        Button backButton = new Button("Back");

        backButton.setOnAction(e -> {
            new MainMenu().show(stage);
        });

        VBox layout = new VBox(10, label, backButton);
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }
}