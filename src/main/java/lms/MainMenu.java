package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    public void show(Stage stage) {

        Label title = new Label("Life Management System");

        Button sleepButton = new Button("Sleep Tracker");
        Button habitButton = new Button("Habit Tracker");
        Button moodButton = new Button("Mood Tracker");
        Button analyticsButton = new Button("Analytics");
        Button logoutButton = new Button("Logout");

        sleepButton.setOnAction(e -> {
            new SleepTrackerScreen().show(stage);
        });

        habitButton.setOnAction(e -> {
            new HabitTrackerScreen().show(stage);
        });

        moodButton.setOnAction(e -> {
            new MoodTrackerScreen().show(stage);
        });

        analyticsButton.setOnAction(e -> {
            new AnalyticsScreen().show(stage);
        });

        logoutButton.setOnAction(e -> {
            new LoginScreen().show(stage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                title,
                sleepButton,
                habitButton,
                moodButton,
                analyticsButton,
                logoutButton
        );

        Scene scene = new Scene(layout, 350, 350);
        stage.setScene(scene);
        stage.show();
    }
}