package lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.database.SleepDAO;
import lms.models.User;

import java.time.LocalDate;

public class SleepTrackerScreen {

    private static final ObservableList<String> sleepData =
            FXCollections.observableArrayList();

    public static ObservableList<String> getData() {
        return sleepData;
    }

    public static int getSleepCount() {
        return sleepData.size();
    }

    public void show(Stage stage, User user) {

        Label title = new Label("Praćenje sna");

        Spinner<Integer> hours = new Spinner<>(0, 24, 8);

        Button saveButton = new Button("Spremi");
        Button backButton = new Button("Nazad");

        ListView<String> listView = new ListView<>(sleepData);

        SleepDAO dao = new SleepDAO();

        sleepData.clear();
        sleepData.addAll(dao.loadSleep(user.getUsername()));

        saveButton.setOnAction(e -> {
            String date = LocalDate.now().toString();
            int h = hours.getValue();

            sleepData.add(date + " - " + h + " sati");
            dao.saveSleep(user.getUsername(), date, h);
        });

        backButton.setOnAction(e ->
                new MainMenu().show(stage, user)
        );

        VBox layout = new VBox(15, title, hours, saveButton, listView, backButton);
        layout.setStyle("-fx-alignment: center;");

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(saveButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 400, 500));
        stage.show();
    }
}