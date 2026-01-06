package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.database.UserDAO;
import lms.models.User;

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

            User user = new User(
                    usernameField.getText(),
                    passwordField.getText(),
                    themeBox.getValue()
            );

            UserDAO dao = new UserDAO();
            dao.insertUser(user);

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