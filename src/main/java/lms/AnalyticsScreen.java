import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.MainMenu;
import lms.ThemeUtil;
import lms.analytics.PdfExportUtil;
import lms.models.User;

public class AnalyticsScreen {

    public void show(Stage stage, User user) {

        Label title = new Label("Analytics");

        Button exportPdfButton = new Button("Export PDF");
        Button backButton = new Button("Nazad");

        exportPdfButton.setOnAction(e -> {
            PdfExportUtil.exportUserReport(user);
        });

        backButton.setOnAction(e -> {
            new MainMenu().show(stage, user);
        });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(
                title,
                exportPdfButton,
                backButton
        );

        if ("Dark".equals(user.getTheme())) {
            ThemeUtil.applyDark(layout);
            ThemeUtil.styleTitleDark(title);
            ThemeUtil.styleButtonDark(exportPdfButton);
            ThemeUtil.styleButtonDark(backButton);
        } else {
            ThemeUtil.applyLight(layout);
            ThemeUtil.styleTitleLight(title);
            ThemeUtil.styleButtonLight(exportPdfButton);
            ThemeUtil.styleButtonLight(backButton);
        }

        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }
}