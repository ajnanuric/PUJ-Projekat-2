package lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

import java.time.LocalDate;

public class MoodTrackerScreen {

    private static final ObservableList<String> moods = FXCollections.observableArrayList();

    public void show(Stage stage, User user) {

        Label title = new Label("Mood Tracker");

        ComboBox<String> moodBox = new ComboBox<>();
        moodBox.getItems().addAll("Sretan", "Neutralan", "Tužan", "Stresiran");
        moodBox.setValue("Sretan");

        Button saveButton = new Button("Spremi");
        Button backButton = new Button("Nazad");

        ListView<String> list = new ListView<>(moods);

        saveButton.setOnAction(e ->
                moods.add(LocalDate.now() + " - " + moodBox.getValue())
        );

        backButton.setOnAction(e -> new MainMenu().show(stage, user));

        VBox layout = new VBox(15, title, moodBox, saveButton, list, backButton);
        layout.setAlignment(Pos.CENTER);

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(saveButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 400, 500));
        stage.show();
    }

    public static ObservableList<String> getMoods() {
        return moods;
    }
}