package lms;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ThemeUtil {

    public static void applyTheme(Pane pane, String theme) {
        if ("Dark".equals(theme)) {
            pane.setStyle("-fx-background-color: #1e1e1e;");
        } else if ("Blue".equals(theme)) {
            pane.setStyle("-fx-background-color: #dbeafe;");
        } else if ("Green".equals(theme)) {
            pane.setStyle("-fx-background-color: #dcfce7;");
        } else {
            pane.setStyle("-fx-background-color: #fce7f3;");
        }
    }

    public static void styleTitle(Label label, String theme) {
        if ("Dark".equals(theme)) {
            label.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");
        } else {
            label.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #111;");
        }
    }

    public static void styleButton(Button button, String theme) {
        if ("Dark".equals(theme)) {
            button.setStyle("-fx-background-color: #3f3f46; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        } else {
            button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #ccc;");
        }
    }
}