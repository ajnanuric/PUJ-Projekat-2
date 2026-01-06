package lms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

public class MainMenu {

    public void show(Stage stage, User user) {

        Label title = new Label("Life Management System");

        Button sleepButton = new Button("Sleep Tracker");
        Button habitButton = new Button("Habit Tracker");
        Button moodButton = new Button("Mood Tracker");
        Button financeButton = new Button("Finance Tracker");
        Button analyticsButton = new Button("Analytics");
        Button logoutButton = new Button("Logout");

        sleepButton.setPrefSize(160, 60);
        habitButton.setPrefSize(160, 60);
        moodButton.setPrefSize(160, 60);
        financeButton.setPrefSize(160, 60);
        analyticsButton.setPrefSize(160, 60);
        logoutButton.setPrefSize(160, 60);

        sleepButton.setOnAction(e -> new SleepTrackerScreen().show(stage, user));
        habitButton.setOnAction(e -> new HabitTrackerScreen().show(stage, user));
        moodButton.setOnAction(e -> new MoodTrackerScreen().show(stage, user));
        financeButton.setOnAction(e -> new FinanceScreen().show(stage, user));
        analyticsButton.setOnAction(e -> new AnalyticsScreen().show(stage, user));
        logoutButton.setOnAction(e -> new LoginScreen().show(stage));

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        grid.add(sleepButton, 0, 0);
        grid.add(habitButton, 1, 0);
        grid.add(moodButton, 2, 0);

        grid.add(financeButton, 0, 1);
        grid.add(analyticsButton, 1, 1);
        grid.add(logoutButton, 2, 1);

        VBox root = new VBox(25);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, grid);

        if ("Dark".equals(user.getTheme())) {
            ThemeUtil.applyDark(root);
            ThemeUtil.styleTitleDark(title);
            ThemeUtil.styleButtonDark(sleepButton);
            ThemeUtil.styleButtonDark(habitButton);
            ThemeUtil.styleButtonDark(moodButton);
            ThemeUtil.styleButtonDark(financeButton);
            ThemeUtil.styleButtonDark(analyticsButton);
            ThemeUtil.styleButtonDark(logoutButton);
        } else {
            ThemeUtil.applyLight(root);
            ThemeUtil.styleTitleLight(title);
            ThemeUtil.styleButtonLight(sleepButton);
            ThemeUtil.styleButtonLight(habitButton);
            ThemeUtil.styleButtonLight(moodButton);
            ThemeUtil.styleButtonLight(financeButton);
            ThemeUtil.styleButtonLight(analyticsButton);
            ThemeUtil.styleButtonLight(logoutButton);
        }

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}