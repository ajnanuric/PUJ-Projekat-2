package lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.database.HabitDAO;
import lms.models.User;

public class HabitTrackerScreen {

    private static final ObservableList<String> habits =
            FXCollections.observableArrayList();

    public static ObservableList<String> getHabits() {
        return habits;
    }

    public static int getHabitCount() {
        return habits.size();
    }

    public void show(Stage stage, User user) {

        Label title = new Label("Praćenje navika");

        TextField habitField = new TextField();
        habitField.setPromptText("Unesi naviku");

        Button addButton = new Button("Dodaj");
        Button backButton = new Button("Nazad");

        ListView<String> listView = new ListView<>(habits);

        HabitDAO dao = new HabitDAO();

        habits.clear();
        habits.addAll(dao.loadHabits(user.getUsername()));

        addButton.setOnAction(e -> {
            String habit = habitField.getText();
            if (!habit.isEmpty()) {
                habits.add(habit);
                dao.saveHabit(user.getUsername(), habit);
                habitField.clear();
            }
        });

        backButton.setOnAction(e ->
                new MainMenu().show(stage, user)
        );

        VBox layout = new VBox(15, title, habitField, addButton, listView, backButton);
        layout.setStyle("-fx-alignment: center;");

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(addButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 400, 500));
        stage.show();
    }
}