package lms;

import finance.FinanceTrackerForm;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.models.User;

import javax.swing.SwingUtilities;

public class FinanceScreen {

    public void show(Stage stage, User user) {

        Label title = new Label("Finance Tracker");

        Button openButton = new Button("Otvori Finance Tracker");
        Button backButton = new Button("Nazad");

        openButton.setOnAction(e ->
                SwingUtilities.invokeLater(FinanceTrackerForm::new)
        );

        backButton.setOnAction(e ->
                new MainMenu().show(stage, user)
        );

        VBox layout = new VBox(15, title, openButton, backButton);

        if ("Dark".equals(user.getTheme())) {
            ThemeUtil.applyDark(layout);
            ThemeUtil.styleTitleDark(title);
            ThemeUtil.styleButtonDark(openButton);
            ThemeUtil.styleButtonDark(backButton);
        } else {
            ThemeUtil.applyLight(layout);
            ThemeUtil.styleTitleLight(title);
            ThemeUtil.styleButtonLight(openButton);
            ThemeUtil.styleButtonLight(backButton);
        }

        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }
}