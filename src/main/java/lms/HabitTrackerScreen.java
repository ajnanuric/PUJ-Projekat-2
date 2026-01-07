package lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

public class HabitTrackerScreen {

    private static final ObservableList<String> habits = FXCollections.observableArrayList();

    public void show(Stage stage, User user) {

        Label title = new Label("Habit Tracker");

        TextField field = new TextField();
        field.setPromptText("Unesi naviku");

        Button addButton = new Button("Dodaj");
        Button backButton = new Button("Nazad");

        ListView<String> list = new ListView<>(habits);

        addButton.setOnAction(e -> {
            if (!field.getText().isEmpty()) {
                habits.add(field.getText());
                field.clear();
            }
        });

        backButton.setOnAction(e -> new MainMenu().show(stage, user));

        VBox layout = new VBox(15, title, field, addButton, list, backButton);
        layout.setAlignment(Pos.CENTER);

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(addButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 400, 500));
        stage.show();
    }

    public static ObservableList<String> getHabits() {
        return habits;
    }
}