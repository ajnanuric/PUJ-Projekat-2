package lms;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.analytics.PdfExportUtil;
import lms.models.User;

public class AnalyticsScreen {

    public void show(Stage stage, User user) {

        Label title = new Label("Analytics");

        Button exportButton = new Button("Export PDF");
        Button backButton = new Button("Nazad");

        exportButton.setOnAction(e ->
                PdfExportUtil.export(user)
        );

        backButton.setOnAction(e -> new MainMenu().show(stage, user));

        VBox layout = new VBox(20, title, exportButton, backButton);
        layout.setAlignment(Pos.CENTER);

        ThemeUtil.applyTheme(layout, user.getTheme());
        ThemeUtil.styleTitle(title, user.getTheme());
        ThemeUtil.styleButton(exportButton, user.getTheme());
        ThemeUtil.styleButton(backButton, user.getTheme());

        stage.setScene(new Scene(layout, 300, 220));
        stage.show();
    }
}