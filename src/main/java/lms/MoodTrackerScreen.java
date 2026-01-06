package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

public class MoodTrackerScreen {

    public void show(Stage stage, User user) {

        Label title = new Label("Mood Tracker");

        ComboBox<String> moodBox = new ComboBox<>();
        moodBox.getItems().addAll("Happy", "Neutral", "Sad", "Stressed");
        moodBox.setValue("Happy");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new MainMenu().show(stage, user));

        VBox layout = new VBox(10, title, moodBox, backButton);

        if ("Dark".equals(user.getTheme())) {
            ThemeUtil.applyDark(layout);
            ThemeUtil.styleTitleDark(title);
            ThemeUtil.styleButtonDark(backButton);
        } else {
            ThemeUtil.applyLight(layout);
            ThemeUtil.styleTitleLight(title);
            ThemeUtil.styleButtonLight(backButton);
        }

        stage.setScene(new Scene(layout, 300, 250));
        stage.show();
    }
}