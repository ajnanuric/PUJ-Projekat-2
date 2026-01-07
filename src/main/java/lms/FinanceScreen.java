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

        VBox layout = new VBox(20, title, openButton, backButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(openButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 300, 220));
        stage.show();
    }
}