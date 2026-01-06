package lms;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SleepTrackerScreen {

    public void show(Stage stage) {

        Label title = new Label("Praćenje sna");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField dayField = new TextField();
        dayField.setPromptText("Dan (npr. Ponedjeljak)");

        TextField hoursField = new TextField();
        hoursField.setPromptText("Broj sati sna");

        TextArea noteArea = new TextArea();
        noteArea.setPromptText("Bilješka (opcionalno)");
        noteArea.setPrefRowCount(3);

        Label statusLabel = new Label();

        Button saveButton = new Button("Sačuvaj");
        Button backButton = new Button("Nazad");

        saveButton.setOnAction(e -> {
            statusLabel.setText("Podaci o snu su sačuvani.");
        });

        backButton.setOnAction(e -> {
            new MainMenu().show(stage);
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(
                title,
                dayField,
                hoursField,
                noteArea,
                saveButton,
                statusLabel,
                backButton
        );

        Scene scene = new Scene(layout, 350, 350);
        stage.setScene(scene);
        stage.show();
    }
}
