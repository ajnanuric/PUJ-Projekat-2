package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

public class AnalyticsScreen {

    public void show(Stage stage, User user) {

        Label title = new Label("Analytics");
        Label info = new Label("Osnovna statistika korisnika");

        Button backButton = new Button("Nazad");
        backButton.setOnAction(e -> new MainMenu().show(stage, user));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                title,
                info,
                backButton
        );

        if ("Dark".equals(user.getTheme())) {
            ThemeUtil.applyDark(layout);
            ThemeUtil.styleTitleDark(title);
            ThemeUtil.styleButtonDark(backButton);
        } else {
            ThemeUtil.applyLight(layout);
            ThemeUtil.styleTitleLight(title);
            ThemeUtil.styleButtonLight(backButton);
        }

        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }
}