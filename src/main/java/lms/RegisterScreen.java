package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterScreen {

    public void show(Stage stage) {

        Label title = new Label("Register");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        ComboBox<String> themeBox = new ComboBox<>();
        themeBox.getItems().addAll("Light", "Dark");
        themeBox.setValue("Light");

        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

        registerButton.setOnAction(e -> {
            // za sada samo ispis (kasnije ide MongoDB)
            System.out.println("User registered:");
            System.out.println("Username: " + usernameField.getText());
            System.out.println("Theme: " + themeBox.getValue());

            // nakon registracije vraćamo se na login
            new LoginScreen().show(stage);
        });

        backButton.setOnAction(e -> {
            new LoginScreen().show(stage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                themeBox,
                registerButton,
                backButton
        );

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
    }
}