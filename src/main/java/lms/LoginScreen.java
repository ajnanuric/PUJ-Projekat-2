package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {

    public void show(Stage stage) {

        Label title = new Label("Login");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setOnAction(e -> {
            // za sada samo prelazimo dalje
            new MainMenu().show(stage);
        });

        registerButton.setOnAction(e -> {
            new RegisterScreen().show(stage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                loginButton,
                registerButton
        );

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
}