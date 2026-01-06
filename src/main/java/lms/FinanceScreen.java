package lms;

import finance.FinanceTrackerForm;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

public class FinanceScreen {

    public void show(Stage stage, User user) {

        Label title = new Label("Finance Tracker");

        Button openButton = new Button("Otvori Finance Tracker");
        Button backButton = new Button("Nazad");

        openButton.setOnAction(e -> {
            new FinanceTrackerForm(); // <-- OVO JE JEDINO BITNO
        });

        backButton.setOnAction(e ->
                new MainMenu().show(stage, user)
        );

        VBox layout = new VBox(15, title, openButton, backButton);
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }
}