package lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

import java.time.LocalDate;

public class SleepTrackerScreen {

    private static final ObservableList<String> data = FXCollections.observableArrayList();

    public void show(Stage stage, User user) {

        Label title = new Label("Sleep Tracker");

        Spinner<Integer> hours = new Spinner<>(0, 24, 8);

        Button saveButton = new Button("Spremi");
        Button backButton = new Button("Nazad");

        ListView<String> list = new ListView<>(data);

        saveButton.setOnAction(e ->
                data.add(LocalDate.now() + " - " + hours.getValue() + " sati")
        );

        backButton.setOnAction(e -> new MainMenu().show(stage, user));

        VBox layout = new VBox(15, title, hours, saveButton, list, backButton);
        layout.setAlignment(Pos.CENTER);

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(saveButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 400, 500));
        stage.show();
    }

    public static ObservableList<String> getData() {
        return data;
    }
}