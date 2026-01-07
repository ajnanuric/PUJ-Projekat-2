package lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.database.MoodDAO;
import lms.models.User;

import java.time.LocalDate;

public class MoodTrackerScreen {

    private static final ObservableList<String> moods =
            FXCollections.observableArrayList();

    public static ObservableList<String> getMoods() {
        return moods;
    }

    public static int getMoodCount() {
        return moods.size();
    }

    public void show(Stage stage, User user) {

        Label title = new Label("Praćenje raspoloženja");

        ComboBox<String> moodBox = new ComboBox<>();
        moodBox.getItems().addAll("Sretan", "Neutralan", "Tužan", "Stresiran");
        moodBox.setValue("Sretan");

        Button saveButton = new Button("Spremi");
        Button backButton = new Button("Nazad");

        ListView<String> listView = new ListView<>(moods);

        MoodDAO dao = new MoodDAO();

        moods.clear();
        moods.addAll(dao.loadMoods(user.getUsername()));

        saveButton.setOnAction(e -> {
            String date = LocalDate.now().toString();
            String mood = moodBox.getValue();

            moods.add(date + " - " + mood);
            dao.saveMood(user.getUsername(), date, mood);
        });

        backButton.setOnAction(e ->
                new MainMenu().show(stage, user)
        );

        VBox layout = new VBox(15, title, moodBox, saveButton, listView, backButton);
        layout.setStyle("-fx-alignment: center;");

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(saveButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 400, 500));
        stage.show();
    }
}