package lms;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import lms.database.UserDAO;
import lms.models.User;

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

            UserDAO dao = new UserDAO();
            User user = dao.findUser(
                    usernameField.getText(),
                    passwordField.getText()
            );

            if (user != null) {
                new MainMenu().show(stage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText(null);
                alert.setContentText("Pogrešno korisničko ime ili lozinka");
                alert.showAndWait();
            }
        });

        // 🔑 OVO JE FALILO
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