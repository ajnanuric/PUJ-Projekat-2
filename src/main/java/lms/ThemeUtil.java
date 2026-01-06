package lms;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ThemeUtil {

    public static void applyLight(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #FFFFFF;"
        );
    }

    public static void applyDark(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #2B2B2B;"
        );
    }

    public static void styleButtonLight(Button button) {
        button.setStyle(
                "-fx-background-color: #E0E0E0;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 10;"
        );
    }

    public static void styleButtonDark(Button button) {
        button.setStyle(
                "-fx-background-color: #444444;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 10;"
        );
    }

    public static void styleTitleLight(Label label) {
        label.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );
    }

    public static void styleTitleDark(Label label) {
        label.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );
    }
    public static void applyPink(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #FADADD;"
        );
    }

}