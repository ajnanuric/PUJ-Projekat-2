package lms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;
import lms.reminder.ReminderUtil;

public class MainMenu {

    public void show(Stage stage, User user) {

        Label title = new Label("Life Management System");

        Label reminderTitle = new Label("Današnji podsjetnik:");
        Label reminderLabel = new Label(ReminderUtil.getRandomReminder());

        ComboBox<String> themeBox = new ComboBox<>();
        themeBox.getItems().addAll("Pink", "Dark", "Blue", "Green");
        themeBox.setValue(user.getTheme());

        Button sleepButton = new Button("Sleep Tracker");
        Button habitButton = new Button("Habit Tracker");
        Button moodButton = new Button("Mood Tracker");
        Button financeButton = new Button("Finance Tracker");
        Button analyticsButton = new Button("Analytics");
        Button logoutButton = new Button("Logout");

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

        VBox root = new VBox(15, title, reminderTitle, reminderLabel, new Label("Tema:"), themeBox, grid);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        applyTheme(root, title, user, sleepButton, habitButton, moodButton, financeButton, analyticsButton, logoutButton);

        themeBox.setOnAction(e -> {
            user.setTheme(themeBox.getValue());
            applyTheme(root, title, user, sleepButton, habitButton, moodButton, financeButton, analyticsButton, logoutButton);
        });

        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    private void applyTheme(VBox root, Label title, User user, Button... buttons) {
        ThemeUtil.applyTheme(root, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        for (Button b : buttons) {
            ThemeUtil.styleButton(b, user.getTheme());
        }
    }
}