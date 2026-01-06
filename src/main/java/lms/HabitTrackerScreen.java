package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

public class HabitTrackerScreen {

    public void show(Stage stage, User user) {

        Label title = new Label("Habit Tracker");

        TextField habitField = new TextField();
        habitField.setPromptText("Habit name");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new MainMenu().show(stage, user));

        VBox layout = new VBox(10, title, habitField, backButton);

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