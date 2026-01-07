package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.achievement.AchievementUtil;
import lms.analytics.PdfExportUtil;
import lms.models.User;

public class AnalyticsScreen {

    public void show(Stage stage, User user) {

        Label title = new Label("Analitika i postignuća");

        ListView<String> achievementsView = new ListView<>();
        achievementsView.getItems().addAll(
                AchievementUtil.getAchievements(
                        SleepTrackerScreen.getSleepCount(),
                        MoodTrackerScreen.getMoodCount(),
                        HabitTrackerScreen.getHabitCount()
                )
        );

        Button exportPdfButton = new Button("Izvezi PDF izvještaj");
        Button backButton = new Button("Nazad");

        exportPdfButton.setOnAction(e ->
                PdfExportUtil.export(user)
        );

        backButton.setOnAction(e ->
                new MainMenu().show(stage, user)
        );

        VBox layout = new VBox(20, title, achievementsView, exportPdfButton, backButton);
        layout.setStyle("-fx-alignment: center;");

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(exportPdfButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 420, 420));
        stage.show();
    }
}