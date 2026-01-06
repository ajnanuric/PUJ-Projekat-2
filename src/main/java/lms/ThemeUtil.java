package lms;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ThemeUtil {


    public static void applyBackground(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #FADADD;"
        );
    }


    public static void styleButton(Button button) {
        button.setStyle(
                "-fx-background-color: #F4A7B9;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 15;"
        );
    }


    public static void styleTitle(Label label) {
        label.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #8B3A62;"
        );
    }
}