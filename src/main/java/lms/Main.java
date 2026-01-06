package lms;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Pokreće Login ekran
        new LoginScreen().show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}